/*
 * Copyright 2014 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaaproject.kaa.server.operations.service.http;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.LinkedList;
import java.util.List;

import org.apache.avro.specific.SpecificRecordBase;
import org.kaaproject.kaa.common.endpoint.gen.SyncRequest;
import org.kaaproject.kaa.common.endpoint.gen.SyncResponse;
import org.kaaproject.kaa.server.common.http.server.BadRequestException;
import org.kaaproject.kaa.server.common.thrift.gen.operations.Notification;
import org.kaaproject.kaa.server.common.thrift.gen.operations.RedirectionRule;
import org.kaaproject.kaa.server.operations.pojo.exceptions.GetDeltaException;
import org.kaaproject.kaa.server.operations.service.OperationsService;
import org.kaaproject.kaa.server.operations.service.akka.AkkaService;
import org.kaaproject.kaa.server.operations.service.akka.messages.io.NettyCommandAwareMessage;
import org.kaaproject.kaa.server.operations.service.akka.messages.io.request.NettyEncodedRequestMessage;
import org.kaaproject.kaa.server.operations.service.http.commands.AbstractOperationsCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorSystem;

/**
 * Test Class implemented AkkaService interface to intercept CommanProcessor messages and process it.
 * @author Andrey Panasenko <apanasenko@cybervisiontech.com>
 *
 */
public class TestAkkaService extends Thread implements AkkaService {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory
            .getLogger(TestAkkaService.class);

    /** Endpoint Service */
    private final OperationsService endpointService;

    /** Boolean operate */
    private boolean operate = true;

    /** List of commands queue */
    private final List<NettyEncodedRequestMessage> commands;

    /**
     * Constructor
     * @param endpointService
     */
    public TestAkkaService(OperationsService endpointService) {
        commands = new LinkedList<>();
        this.endpointService = endpointService;
    }

    /* (non-Javadoc)
     * @see org.kaaproject.kaa.server.operations.service.akka.AkkaService#getActorSystem()
     */
    @Override
    public ActorSystem getActorSystem() {
        return null;
    }


    /* (non-Javadoc)
     * @see org.kaaproject.kaa.server.operations.service.akka.AkkaService#onRedirectionRule(org.kaaproject.kaa.server.common.thrift.gen.operations.RedirectionRule)
     */
    @Override
    public void onRedirectionRule(RedirectionRule redirectionRule) {

    }

    /* (non-Javadoc)
     * @see org.kaaproject.kaa.server.operations.service.akka.AkkaService#onNotification(org.kaaproject.kaa.server.common.thrift.gen.operations.Notification)
     */
    @Override
    public void onNotification(Notification notification) {

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        logger.info("Test Akka Service started: ");
        setName("Test_Service:");
        while(operate) {
            NettyEncodedRequestMessage command = null;
            try {
                synchronized (commands) {
                    if (commands.size() > 0) {
                        command = commands.remove(0);
                    } else {
                        commands.wait();
                    }
                }
            } catch (InterruptedException ie) {

            }
            if (command != null) {
                process(command);
            }
        }
        logger.info("Test Akka Service stoped: ");
    }

    /**
     * Shutdown method, block until thread complete.
     */
    public void shutdown() {
        operate = false;
        logger.info("Test Akka Service shutdowning.....");
        synchronized (commands) {
            commands.notify();
        }
        try {
            this.join(10000);
        } catch (InterruptedException e) {
        }
        logger.info("Test Akka Service shutdowning..... Complete.");
    }

    /**
     * Add command to queue
     * @param command NettyEncodedRequestMessage
     */
    public void addCommand(NettyEncodedRequestMessage command) {
        synchronized (commands) {
            commands.add(command);
            commands.notify();
        }
    }

    /**
     * Process command
     * @param command NettyEncodedRequestMessage
     */
    private void process(NettyEncodedRequestMessage command) {

        try {
            SpecificRecordBase request = command.getCommand().decode();

            command.getCommand().process();


            command.getCommand().encode(getResponse(request, command.getCommand()));

            command.getChannelContext().writeAndFlush(command.getCommand());

        } catch (BadRequestException | GeneralSecurityException | IOException | GetDeltaException e) {
            command.getChannelContext().fireExceptionCaught(e);
        }

    }

    /**
     * Generate response
     * @param request SpecificRecordBase
     * @param command AbstractEndpointCommand
     * @return SpecificRecordBase
     * @throws BadRequestException
     * @throws GeneralSecurityException
     * @throws IOException
     * @throws GetDeltaException
     */
    private SpecificRecordBase getResponse(SpecificRecordBase request, AbstractOperationsCommand<SpecificRecordBase, SpecificRecordBase> command)
            throws BadRequestException, GeneralSecurityException, IOException, GetDeltaException {

        if (request == null || command == null) {
            throw new BadRequestException("Error processing");
        }

        SpecificRecordBase response = null;

        if (request instanceof SyncRequest) {
            OperationsHttpServerIT.SyncTestSetRequestReceived(((SyncRequest) request).getConfigurationSyncRequest().getAppStateSeqNumber().intValue(),
                        (SyncRequest) request);

            response = endpointService.sync((SyncRequest) request).getResponse();

            OperationsHttpServerIT.SyncTestSetResponseSent(((SyncRequest) request).getConfigurationSyncRequest().getAppStateSeqNumber().intValue(),
                        (SyncResponse) response);
        } else {
            throw new BadRequestException("invalid instance of request");
        }

        return response;
    }

    @Override
    public void process(NettyCommandAwareMessage message) {
        // TODO Auto-generated method stub

    }
}