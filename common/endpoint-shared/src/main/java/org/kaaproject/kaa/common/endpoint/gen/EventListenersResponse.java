/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package org.kaaproject.kaa.common.endpoint.gen;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class EventListenersResponse extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"EventListenersResponse\",\"namespace\":\"org.kaaproject.kaa.common.endpoint.gen\",\"fields\":[{\"name\":\"requestId\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"listeners\",\"type\":[{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},\"null\"]},{\"name\":\"result\",\"type\":{\"type\":\"enum\",\"name\":\"SyncResponseResultType\",\"symbols\":[\"SUCCESS\",\"FAILURE\",\"PROFILE_RESYNC\",\"REDIRECT\"]}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
   private java.lang.String requestId;
   private java.util.List<java.lang.String> listeners;
   private org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType result;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use {@link \#newBuilder()}. 
   */
  public EventListenersResponse() {}

  /**
   * All-args constructor.
   */
  public EventListenersResponse(java.lang.String requestId, java.util.List<java.lang.String> listeners, org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType result) {
    this.requestId = requestId;
    this.listeners = listeners;
    this.result = result;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return requestId;
    case 1: return listeners;
    case 2: return result;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: requestId = (java.lang.String)value$; break;
    case 1: listeners = (java.util.List<java.lang.String>)value$; break;
    case 2: result = (org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'requestId' field.
   */
  public java.lang.String getRequestId() {
    return requestId;
  }

  /**
   * Sets the value of the 'requestId' field.
   * @param value the value to set.
   */
  public void setRequestId(java.lang.String value) {
    this.requestId = value;
  }

  /**
   * Gets the value of the 'listeners' field.
   */
  public java.util.List<java.lang.String> getListeners() {
    return listeners;
  }

  /**
   * Sets the value of the 'listeners' field.
   * @param value the value to set.
   */
  public void setListeners(java.util.List<java.lang.String> value) {
    this.listeners = value;
  }

  /**
   * Gets the value of the 'result' field.
   */
  public org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType getResult() {
    return result;
  }

  /**
   * Sets the value of the 'result' field.
   * @param value the value to set.
   */
  public void setResult(org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType value) {
    this.result = value;
  }

  /** Creates a new EventListenersResponse RecordBuilder */
  public static org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder newBuilder() {
    return new org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder();
  }
  
  /** Creates a new EventListenersResponse RecordBuilder by copying an existing Builder */
  public static org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder newBuilder(org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder other) {
    return new org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder(other);
  }
  
  /** Creates a new EventListenersResponse RecordBuilder by copying an existing EventListenersResponse instance */
  public static org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder newBuilder(org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse other) {
    return new org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder(other);
  }
  
  /**
   * RecordBuilder for EventListenersResponse instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<EventListenersResponse>
    implements org.apache.avro.data.RecordBuilder<EventListenersResponse> {

    private java.lang.String requestId;
    private java.util.List<java.lang.String> listeners;
    private org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType result;

    /** Creates a new Builder */
    private Builder() {
      super(org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.requestId)) {
        this.requestId = data().deepCopy(fields()[0].schema(), other.requestId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.listeners)) {
        this.listeners = data().deepCopy(fields()[1].schema(), other.listeners);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.result)) {
        this.result = data().deepCopy(fields()[2].schema(), other.result);
        fieldSetFlags()[2] = true;
      }
    }
    
    /** Creates a Builder by copying an existing EventListenersResponse instance */
    private Builder(org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse other) {
            super(org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.SCHEMA$);
      if (isValidValue(fields()[0], other.requestId)) {
        this.requestId = data().deepCopy(fields()[0].schema(), other.requestId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.listeners)) {
        this.listeners = data().deepCopy(fields()[1].schema(), other.listeners);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.result)) {
        this.result = data().deepCopy(fields()[2].schema(), other.result);
        fieldSetFlags()[2] = true;
      }
    }

    /** Gets the value of the 'requestId' field */
    public java.lang.String getRequestId() {
      return requestId;
    }
    
    /** Sets the value of the 'requestId' field */
    public org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder setRequestId(java.lang.String value) {
      validate(fields()[0], value);
      this.requestId = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'requestId' field has been set */
    public boolean hasRequestId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'requestId' field */
    public org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder clearRequestId() {
      requestId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'listeners' field */
    public java.util.List<java.lang.String> getListeners() {
      return listeners;
    }
    
    /** Sets the value of the 'listeners' field */
    public org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder setListeners(java.util.List<java.lang.String> value) {
      validate(fields()[1], value);
      this.listeners = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'listeners' field has been set */
    public boolean hasListeners() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'listeners' field */
    public org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder clearListeners() {
      listeners = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'result' field */
    public org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType getResult() {
      return result;
    }
    
    /** Sets the value of the 'result' field */
    public org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder setResult(org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType value) {
      validate(fields()[2], value);
      this.result = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'result' field has been set */
    public boolean hasResult() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'result' field */
    public org.kaaproject.kaa.common.endpoint.gen.EventListenersResponse.Builder clearResult() {
      result = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public EventListenersResponse build() {
      try {
        EventListenersResponse record = new EventListenersResponse();
        record.requestId = fieldSetFlags()[0] ? this.requestId : (java.lang.String) defaultValue(fields()[0]);
        record.listeners = fieldSetFlags()[1] ? this.listeners : (java.util.List<java.lang.String>) defaultValue(fields()[1]);
        record.result = fieldSetFlags()[2] ? this.result : (org.kaaproject.kaa.common.endpoint.gen.SyncResponseResultType) defaultValue(fields()[2]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}