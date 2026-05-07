package com.extreme;



@javax.annotation.processing.Generated("dsl_json")
public class _UserHandler$UserDTO_DslJsonConverter implements com.dslplatform.json.Configuration {
	private static final java.nio.charset.Charset utf8 = java.nio.charset.Charset.forName("UTF-8");
	@Override
	public void configure(com.dslplatform.json.DslJson __dsljson) {
		ObjectFormatConverter objectConverter = new ObjectFormatConverter(__dsljson);
		__dsljson.registerBinder(com.extreme.UserHandler.UserDTO.class, objectConverter);
		__dsljson.registerReader(com.extreme.UserHandler.UserDTO.class, objectConverter);
		__dsljson.registerWriter(com.extreme.UserHandler.UserDTO.class, objectConverter);
	}
	public final static class ObjectFormatConverter implements com.dslplatform.json.runtime.FormatConverter<com.extreme.UserHandler.UserDTO>, com.dslplatform.json.JsonReader.BindObject<com.extreme.UserHandler.UserDTO> {
		private final boolean alwaysSerialize;
		private final com.dslplatform.json.DslJson __dsljson;
		public ObjectFormatConverter(com.dslplatform.json.DslJson __dsljson) {
			this.alwaysSerialize = !__dsljson.omitDefaults;
			this.__dsljson = __dsljson;
		}
		public com.extreme.UserHandler.UserDTO read(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			if (reader.wasNull()) return null;
			return bind(reader, new com.extreme.UserHandler.UserDTO());
		}
		private static final byte[] quoted_id = "\"id\":".getBytes(utf8);
		private static final byte[] name_id = "id".getBytes(utf8);
		private static final byte[] quoted_name = ",\"name\":".getBytes(utf8);
		private static final byte[] name_name = "name".getBytes(utf8);
		public final void write(final com.dslplatform.json.JsonWriter writer, final com.extreme.UserHandler.UserDTO instance) {
			if (instance == null) writer.writeNull();
			else {
				writer.writeByte((byte)'{');
				if (alwaysSerialize) { writeContentFull(writer, instance); writer.writeByte((byte)'}'); }
				else if (writeContentMinimal(writer, instance)) writer.getByteBuffer()[writer.size() - 1] = '}';
				else writer.writeByte((byte)'}');
			}
		}
		public void writeContentFull(final com.dslplatform.json.JsonWriter writer, final com.extreme.UserHandler.UserDTO instance) {
			writer.writeAscii(quoted_id);
			if (instance.id == null) writer.writeNull();
			else com.dslplatform.json.NumberConverter.serialize(instance.id, writer);
			writer.writeAscii(quoted_name);
			if (instance.name == null) writer.writeNull();
			else com.dslplatform.json.StringConverter.serialize(instance.name, writer);
		}
		public boolean writeContentMinimal(final com.dslplatform.json.JsonWriter writer, final com.extreme.UserHandler.UserDTO instance) {
			boolean hasWritten = false;
			if (instance.id != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_id); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.NumberConverter.serialize(instance.id, writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			if (instance.name != null) {
				writer.writeByte((byte)'"'); writer.writeAscii(name_name); writer.writeByte((byte)'"'); writer.writeByte((byte)':');
				com.dslplatform.json.StringConverter.serialize(instance.name, writer);
				writer.writeByte((byte)','); hasWritten = true;
			}
			return hasWritten;
		}
		public com.extreme.UserHandler.UserDTO bind(final com.dslplatform.json.JsonReader reader, final com.extreme.UserHandler.UserDTO instance) throws java.io.IOException {
			if (reader.last() != '{') throw reader.newParseError("Expecting '{' for object start");
			reader.getNextToken();
			bindContent(reader, instance);
			return instance;
		}
		public com.extreme.UserHandler.UserDTO readContent(final com.dslplatform.json.JsonReader reader) throws java.io.IOException {
			com.extreme.UserHandler.UserDTO instance = new com.extreme.UserHandler.UserDTO();
 			bindContent(reader, instance);
			return instance;
		}
		public void bindContent(final com.dslplatform.json.JsonReader reader, final com.extreme.UserHandler.UserDTO instance) throws java.io.IOException {
			if (reader.last() == '}') return;
			if (reader.fillNameWeakHash() != 205 || !reader.wasLastName(name_id)) { bindSlow(reader, instance, 0); return; }
			reader.getNextToken();
			instance.id = com.dslplatform.json.NumberConverter.NULLABLE_INT_READER.read(reader);
			if (reader.getNextToken() == '}')  return;
			if (reader.last() != ',') throw reader.newParseError("Expecting ',' for other mandatory properties"); else reader.getNextToken();
			if (reader.fillNameWeakHash() != 417 || !reader.wasLastName(name_name)) { bindSlow(reader, instance, 1); return; }
			reader.getNextToken();
			instance.name = com.dslplatform.json.StringConverter.READER.read(reader);
			if (reader.getNextToken() != '}') {
				if (reader.last() == ',') {
					reader.getNextToken();
					reader.fillNameWeakHash();
					bindSlow(reader, instance, 2);
				}
				if (reader.last() != '}') throw reader.newParseError("Expecting '}' for object end");
			}
		}
		private void bindSlow(final com.dslplatform.json.JsonReader reader, final com.extreme.UserHandler.UserDTO instance, int index) throws java.io.IOException {
			switch(reader.getLastHash()) {
				case -1925595674:
					reader.getNextToken();
					instance.name = com.dslplatform.json.StringConverter.READER.read(reader);
					reader.getNextToken();
					break;
				case 926444256:
					reader.getNextToken();
					instance.id = com.dslplatform.json.NumberConverter.NULLABLE_INT_READER.read(reader);
					reader.getNextToken();
					break;
				default:
					reader.getNextToken();
					reader.skip();
			}
			while (reader.last() == ','){
				reader.getNextToken();
				switch(reader.fillName()) {
					case -1925595674:
						reader.getNextToken();
						instance.name = com.dslplatform.json.StringConverter.READER.read(reader);
						reader.getNextToken();
						break;
					case 926444256:
						reader.getNextToken();
						instance.id = com.dslplatform.json.NumberConverter.NULLABLE_INT_READER.read(reader);
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
