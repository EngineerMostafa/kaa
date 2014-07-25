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

package org.kaaproject.kaa.server.common.dao.mongo;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.kaaproject.kaa.server.common.dao.EndpointProfileDao;
import org.kaaproject.kaa.server.common.dao.mongo.model.EndpointProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;

@Repository
public class EndpointProfileMongoDao extends AbstractMongoDao<EndpointProfile> implements EndpointProfileDao<EndpointProfile> {

    private static final Logger LOG = LoggerFactory.getLogger(EndpointProfileMongoDao.class);

    private static final String ENDPOINT_KEY_HASH = "endpoint_key_hash";

    @Override
    protected String getCollectionName() {
        return EndpointProfile.COLLECTION_NAME;
    }

    @Override
    protected Class<EndpointProfile> getDocumentClass() {
        return EndpointProfile.class;
    }

    @Override
    public EndpointProfile findByKeyHash(byte[] endpointKeyHash) {
        LOG.debug("Find endpoint profile by endpoint key hash [{}] ", endpointKeyHash);
        DBObject dbObject = query(where(ENDPOINT_KEY_HASH).is(endpointKeyHash)).getQueryObject();
        DBObject result = mongoTemplate.getDb().getCollection(EndpointProfile.COLLECTION_NAME).findOne(dbObject);
        return mongoTemplate.getConverter().read(getDocumentClass(), result);
    }

    @Override
    public long getCountByKeyHash(byte[] endpointKeyHash) {
        LOG.debug("Get count of endpoint profiles by endpoint key hash [{}] ", endpointKeyHash);
        DBObject dbObject = query(where(ENDPOINT_KEY_HASH).is(endpointKeyHash)).getQueryObject();
        return mongoTemplate.getDb().getCollection(EndpointProfile.COLLECTION_NAME).count(dbObject);
    }

    @Override
    public void removeByKeyHash(byte[] endpointKeyHash) {
        LOG.debug("Remove endpoint profile by endpoint key hash [{}] ", endpointKeyHash);
        mongoTemplate.remove(query(where(ENDPOINT_KEY_HASH).is(endpointKeyHash)), getCollectionName());
    }

    @Override
    public void removeByAppId(String appId) {
        LOG.debug("Remove endpoint profile by application id [{}] ", appId);
        remove(query(where(APPLICATION_ID).is(appId)));
    }
}