/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class ScoreEvents extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -4454545207332422477L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ScoreEvents\",\"namespace\":\"model\",\"fields\":[{\"name\":\"score\",\"type\":\"double\"},{\"name\":\"product_id\",\"type\":\"long\"},{\"name\":\"player_id\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<ScoreEvents> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<ScoreEvents> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<ScoreEvents> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<ScoreEvents> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<ScoreEvents> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this ScoreEvents to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a ScoreEvents from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a ScoreEvents instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static ScoreEvents fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private double score;
  private long product_id;
  private long player_id;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ScoreEvents() {}

  /**
   * All-args constructor.
   * @param score The new value for score
   * @param product_id The new value for product_id
   * @param player_id The new value for player_id
   */
  public ScoreEvents(java.lang.Double score, java.lang.Long product_id, java.lang.Long player_id) {
    this.score = score;
    this.product_id = product_id;
    this.player_id = player_id;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return score;
    case 1: return product_id;
    case 2: return player_id;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: score = (java.lang.Double)value$; break;
    case 1: product_id = (java.lang.Long)value$; break;
    case 2: player_id = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'score' field.
   * @return The value of the 'score' field.
   */
  public double getScore() {
    return score;
  }


  /**
   * Sets the value of the 'score' field.
   * @param value the value to set.
   */
  public void setScore(double value) {
    this.score = value;
  }

  /**
   * Gets the value of the 'product_id' field.
   * @return The value of the 'product_id' field.
   */
  public long getProductId() {
    return product_id;
  }


  /**
   * Sets the value of the 'product_id' field.
   * @param value the value to set.
   */
  public void setProductId(long value) {
    this.product_id = value;
  }

  /**
   * Gets the value of the 'player_id' field.
   * @return The value of the 'player_id' field.
   */
  public long getPlayerId() {
    return player_id;
  }


  /**
   * Sets the value of the 'player_id' field.
   * @param value the value to set.
   */
  public void setPlayerId(long value) {
    this.player_id = value;
  }

  /**
   * Creates a new ScoreEvents RecordBuilder.
   * @return A new ScoreEvents RecordBuilder
   */
  public static model.ScoreEvents.Builder newBuilder() {
    return new model.ScoreEvents.Builder();
  }

  /**
   * Creates a new ScoreEvents RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ScoreEvents RecordBuilder
   */
  public static model.ScoreEvents.Builder newBuilder(model.ScoreEvents.Builder other) {
    if (other == null) {
      return new model.ScoreEvents.Builder();
    } else {
      return new model.ScoreEvents.Builder(other);
    }
  }

  /**
   * Creates a new ScoreEvents RecordBuilder by copying an existing ScoreEvents instance.
   * @param other The existing instance to copy.
   * @return A new ScoreEvents RecordBuilder
   */
  public static model.ScoreEvents.Builder newBuilder(model.ScoreEvents other) {
    if (other == null) {
      return new model.ScoreEvents.Builder();
    } else {
      return new model.ScoreEvents.Builder(other);
    }
  }

  /**
   * RecordBuilder for ScoreEvents instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ScoreEvents>
    implements org.apache.avro.data.RecordBuilder<ScoreEvents> {

    private double score;
    private long product_id;
    private long player_id;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(model.ScoreEvents.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.score)) {
        this.score = data().deepCopy(fields()[0].schema(), other.score);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.product_id)) {
        this.product_id = data().deepCopy(fields()[1].schema(), other.product_id);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.player_id)) {
        this.player_id = data().deepCopy(fields()[2].schema(), other.player_id);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing ScoreEvents instance
     * @param other The existing instance to copy.
     */
    private Builder(model.ScoreEvents other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.score)) {
        this.score = data().deepCopy(fields()[0].schema(), other.score);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.product_id)) {
        this.product_id = data().deepCopy(fields()[1].schema(), other.product_id);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.player_id)) {
        this.player_id = data().deepCopy(fields()[2].schema(), other.player_id);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'score' field.
      * @return The value.
      */
    public double getScore() {
      return score;
    }


    /**
      * Sets the value of the 'score' field.
      * @param value The value of 'score'.
      * @return This builder.
      */
    public model.ScoreEvents.Builder setScore(double value) {
      validate(fields()[0], value);
      this.score = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'score' field has been set.
      * @return True if the 'score' field has been set, false otherwise.
      */
    public boolean hasScore() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'score' field.
      * @return This builder.
      */
    public model.ScoreEvents.Builder clearScore() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'product_id' field.
      * @return The value.
      */
    public long getProductId() {
      return product_id;
    }


    /**
      * Sets the value of the 'product_id' field.
      * @param value The value of 'product_id'.
      * @return This builder.
      */
    public model.ScoreEvents.Builder setProductId(long value) {
      validate(fields()[1], value);
      this.product_id = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'product_id' field has been set.
      * @return True if the 'product_id' field has been set, false otherwise.
      */
    public boolean hasProductId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'product_id' field.
      * @return This builder.
      */
    public model.ScoreEvents.Builder clearProductId() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'player_id' field.
      * @return The value.
      */
    public long getPlayerId() {
      return player_id;
    }


    /**
      * Sets the value of the 'player_id' field.
      * @param value The value of 'player_id'.
      * @return This builder.
      */
    public model.ScoreEvents.Builder setPlayerId(long value) {
      validate(fields()[2], value);
      this.player_id = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'player_id' field has been set.
      * @return True if the 'player_id' field has been set, false otherwise.
      */
    public boolean hasPlayerId() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'player_id' field.
      * @return This builder.
      */
    public model.ScoreEvents.Builder clearPlayerId() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ScoreEvents build() {
      try {
        ScoreEvents record = new ScoreEvents();
        record.score = fieldSetFlags()[0] ? this.score : (java.lang.Double) defaultValue(fields()[0]);
        record.product_id = fieldSetFlags()[1] ? this.product_id : (java.lang.Long) defaultValue(fields()[1]);
        record.player_id = fieldSetFlags()[2] ? this.player_id : (java.lang.Long) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<ScoreEvents>
    WRITER$ = (org.apache.avro.io.DatumWriter<ScoreEvents>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<ScoreEvents>
    READER$ = (org.apache.avro.io.DatumReader<ScoreEvents>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeDouble(this.score);

    out.writeLong(this.product_id);

    out.writeLong(this.player_id);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.score = in.readDouble();

      this.product_id = in.readLong();

      this.player_id = in.readLong();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.score = in.readDouble();
          break;

        case 1:
          this.product_id = in.readLong();
          break;

        case 2:
          this.player_id = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









