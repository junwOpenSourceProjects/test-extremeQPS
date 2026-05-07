package com.extreme;



@javax.annotation.processing.Generated("dsl_json")
public class _UserHandler$ComplexDTO_DslJsonConverter implements com.dslplatform.json.Configuration {
	private static final java.nio.charset.Charset utf8 = java.nio.charset.Charset.forName("UTF-8");
	@Override
	public void configure(com.dslplatform.json.DslJson __dsljson) {
		ObjectFormatConverter objectConverter = new ObjectFormatConverter(__dsljson);
		__dsljson.registerBinder(com.extreme.UserHandler.ComplexDTO.class, objectConverter);
		__dsljson.registerReader(com.extreme.UserHandler.ComplexDTO.class, objectConverter);
		__dsljson.registerWriter(com.extreme.UserHandler.ComplexDTO.class, objectConverter);
	}
	public final static class ObjectFormatConverter implements com.dslplatform.json.runtime.FormatConverter<com.extreme.UserHandler.ComplexDTO>, com.dslplatform.json.JsonReader.BindObject<com.extreme.UserHandler.ComplexDTO> {
		private final boolean alwaysSerialize;
		private final com.dslplatform.json.DslJson __dsljson;
		private final com.dslplatform.json.JsonReader.ReadObject<java.lang.String> reader_tags;
		private final com.dslplatform.json.JsonWriter.WriteObject<java.lang.String> writer_tags;
		public ObjectFormatConverter(com.dslplatform.json.DslJson __dsljson) {
			this.alwaysSerialize = !__dsljson.omitDefaults;
			this.__dsljson = __dsljson;
			this.reader_tags = com.dslplatform.json.StringConverter.READER;
			this.writer_tags = com.dslplatform.json.StringConverter.WRITER;
		}
		public com.extreme.UserHandler.ComplexDTO read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if (reader.wasNull()) return null;
			return bind(reader, new com.extreme.UserHandler.ComplexDTO());
		}
		private static final byte[] quoted_tags = "\"tags\":".getBytes(utf8);
		private static final byte[] name_tags = "tags".getBytes(utf8);
		private static final byte[] quoted_status = ",\"status\":".getBytes(utf8);
		private static final byte[] name_status = "status".getBytes(utf8);
		private static final byte[] quoted_title = ",\"title\":".getBytes(utf8);
		private static final byte[] name_title = "title".getBytes(utf8);
		private static final byte[] quoted_id = ",\"id\":".getBytes(utf8);
		private static final byte[] name_id = "id".getBytes(utf8);
		private static final byte[] quoted_description = ",\"description\":".getBytes(utf8);
		private static final byte[] name_description = "description".getBytes(utf8);
		private static final byte[] quoted_author = ",\"author\":".getBytes(utf8);
		private static final byte[] name_author = "author".getBytes(utf8);
		private static final byte[] quoted_createdAt = ",\"createdAt\":".getBytes(utf8);
		private static final byte[] name_createdAt = "createdAt".getBytes(utf8);
		public final void write(final com.dslplatform.json.JsonWriter writer, final com.extreme.UserHandler.ComplexDTO instance) {
			if (instance == null) writer.writeNull();
			else {
				writer.writeByte((byte)'{');
				if (alwaysSerialize) { writeContentFull(writer, instance); writer.writeByte((byte)'}'); }
				else if (writeContentMinimal(writer, instance)) writer.getByteBuffer()[writer.size() - 1] = '}';
				else writer.writeByte((byte)'}');
			}
		}
		public void writeContentFull(final com.dslplatform.json.JsonWriter writer, final com.extreme.UserHandler.ComplexDTO instance) {
			writer.writeAscii(quoted_tags);
			if (instance.tags == null) writer.writeNull();
			else writer.serialize(instance.tags, writer_tags);
			writer.writeAscii(quoted_status);
			if (instance.status == null) writer.writeNull();
			else com.dslplatform.json.NumberConverter.serialize(instance.status, writer);
			writer.writeAscii(quoted_title);
			if (instance.title == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.title, writer);
			writer.writeAscii(quoted_id);
			if (instance.id == null) writer.writeNull();
			else com.dslplatform.json.NumberConverter.serialize(instance.id, writer);
			writer.writeAscii(quoted_description);
			if (instance.description == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.description, writer);
			writer.writeAscii(quoted_author);
			if (instance.author == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.author, writer);
			writer.writeAscii(quoted_createdAt);
			if (instance.createdAt == null) writer.writeNull();
			else com.dslplatform.json.NumberConverter.serialize(instance.createdAt, writer);
		}
		public boolean writeContentMinimal(final com.dslplatform.json.JsonWriter writer, final com.extreme.UserHandler.ComplexDTO instance) {
			boolean hasWritten = false;
			if (instance.tags != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_tags); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				writer.serialize(instance.tags, writer_tags);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.status != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_status); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.NumberConverter.serialize(instance.status, writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.title != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_title); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.title, writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.id != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_id); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.NumberConverter.serialize(instance.id, writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.description != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_description); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.description, writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.author != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_author); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.author, writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.createdAt != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_createdAt); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.NumberConverter.serialize(instance.createdAt, writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			return hasWritten;
		}
		public com.extreme.UserHandler.ComplexDTO bind(final com.dslplatform.json.JsonReader reader, final com.extreme.UserHandler.ComplexDTO instance) throws java.io.IOException {
			if (reader.last() != '{') throw reader.newParseError("Expecting '{' for object start");
			reader.getNextToken();
			bindContent(reader, instance);
			return instance;
		}
		public com.extreme.UserHandler.ComplexDTO readContent(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			com.extreme.UserHandler.ComplexDTO instance = new com.extreme.UserHandler.ComplexDTO();
 			bindContent(reader, instance);
			return instance;
		}
		public void bindContent(final com.dslplatform.json.JsonReader reader, final com.extreme.UserHandler.ComplexDTO instance) throws java.io.IOException {
			if (reader.last() == '}') return;
			if (reader.fillNameWeakHash() != 431 || !reader.wasLastName(name_tags)) { bindSlow(reader, instance, 0); return; }
			reader.getNextToken();
			instance.tags = reader.readCollection(reader_tags);
			if (reader.getNextToken() == '}')  return;
			if (reader.last() != ',') throw reader.newParseError("Expecting ',' for other mandatory properties"); else reader.getNextToken();
			if (reader.fillNameWeakHash() != 676 || !reader.wasLastName(name_status)) { bindSlow(reader, instance, 1); return; }
			reader.getNextToken();
			instance.status = com.dslplatform.json.NumberConverter.NULLABLE_INT_READER.read(reader);
			if (reader.getNextToken() == '}')  return;
			if (reader.last() != ',') throw reader.newParseError("Expecting ',' for other mandatory properties"); else reader.getNextToken();
			if (reader.fillNameWeakHash() != 546 || !reader.wasLastName(name_title)) { bindSlow(reader, instance, 2); return; }
			reader.getNextToken();
			instance.title = com.dslplatform.json.StringConverter.READER.read(reader);
			if (reader.getNextToken() == '}')  return;
			if (reader.last() != ',') throw reader.newParseError("Expecting ',' for other mandatory properties"); else reader.getNextToken();
			if (reader.fillNameWeakHash() != 205 || !reader.wasLastName(name_id)) { bindSlow(reader, instance, 3); return; }
			reader.getNextToken();
			instance.id = com.dslplatform.json.NumberConverter.NULLABLE_LONG_READER.read(reader);
			if (reader.getNextToken() == '}')  return;
			if (reader.last() != ',') throw reader.newParseError("Expecting ',' for other mandatory properties"); else reader.getNextToken();
			if (reader.fillNameWeakHash() != 1188 || !reader.wasLastName(name_description)) { bindSlow(reader, instance, 4); return; }
			reader.getNextToken();
			instance.description = com.dslplatform.json.StringConverter.READER.read(reader);
			if (reader.getNextToken() == '}')  return;
			if (reader.last() != ',') throw reader.newParseError("Expecting ',' for other mandatory properties"); else reader.getNextToken();
			if (reader.fillNameWeakHash() != 659 || !reader.wasLastName(name_author)) { bindSlow(reader, instance, 5); return; }
			reader.getNextToken();
			instance.author = com.dslplatform.json.StringConverter.READER.read(reader);
			if (reader.getNextToken() == '}')  return;
			if (reader.last() != ',') throw reader.newParseError("Expecting ',' for other mandatory properties"); else reader.getNextToken();
			if (reader.fillNameWeakHash() != 909 || !reader.wasLastName(name_createdAt)) { bindSlow(reader, instance, 6); return; }
			reader.getNextToken();
			instance.createdAt = com.dslplatform.json.NumberConverter.NULLABLE_LONG_READER.read(reader);
			if (reader.getNextToken() != '}') {
				if (reader.last() == ',') {
					reader.getNextToken();
					reader.fillNameWeakHash();
					bindSlow(reader, instance, 7);
				}
				if (reader.last() != '}') throw reader.newParseError("Expecting '}' for object end");
			}
		}
		private void bindSlow(final com.dslplatform.json.JsonReader reader, final com.extreme.UserHandler.ComplexDTO instance, int index) throws java.io.IOException {
			switch(reader.getLastHash()) {
				case 370722958:
					reader.getNextToken();
					instance.createdAt = com.dslplatform.json.NumberConverter.NULLABLE_LONG_READER.read(reader);
					reader.getNextToken();
					break;
				case 1333443158:
					reader.getNextToken();
					instance.author = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				case 879704937:
					reader.getNextToken();
					instance.description = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				case 926444256:
					reader.getNextToken();
					instance.id = com.dslplatform.json.NumberConverter.NULLABLE_LONG_READER.read(reader);
					reader.getNextToken();
					break;
				case -1738164983:
					reader.getNextToken();
					instance.title = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				case -1169459217:
					reader.getNextToken();
					instance.status = com.dslplatform.json.NumberConverter.NULLABLE_INT_READER.read(reader);
					reader.getNextToken();
					break;
				case -199824480:
					reader.getNextToken();
					instance.tags = reader.readCollection(reader_tags);
					reader.getNextToken();
					break;
				default:
					reader.getNextToken();
					reader.skip();
			}
			while (reader.last() == ','){
				reader.getNextToken();
				switch(reader.fillName()) {
					case 370722958:
						reader.getNextToken();
						instance.createdAt = com.dslplatform.json.NumberConverter.NULLABLE_LONG_READER.read(reader);
						reader.getNextToken();
						break;
					case 1333443158:
						reader.getNextToken();
						instance.author = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					case 879704937:
						reader.getNextToken();
						instance.description = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					case 926444256:
						reader.getNextToken();
						instance.id = com.dslplatform.json.NumberConverter.NULLABLE_LONG_READER.read(reader);
						reader.getNextToken();
						break;
					case -1738164983:
						reader.getNextToken();
						instance.title = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					case -1169459217:
						reader.getNextToken();
						instance.status = com.dslplatform.json.NumberConverter.NULLABLE_INT_READER.read(reader);
						reader.getNextToken();
						break;
					case -199824480:
						reader.getNextToken();
						instance.tags = reader.readCollection(reader_tags);
						reader.getNextToken();
						break;
					default:
						reader.getNextToken();
						reader.skip();
				}
			}
			if (reader.last() != '}') throw reader.newParseError("Expecting '}' for object end");
		}
	}
}
