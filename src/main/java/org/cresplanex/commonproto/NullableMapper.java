package org.cresplanex.commonproto;

import com.google.protobuf.ByteString;

import build.buf.gen.cresplanex.nullable.v1.NullableBool;
import build.buf.gen.cresplanex.nullable.v1.NullableBytes;
import build.buf.gen.cresplanex.nullable.v1.NullableDouble;
import build.buf.gen.cresplanex.nullable.v1.NullableFloat;
import build.buf.gen.cresplanex.nullable.v1.NullableInt32;
import build.buf.gen.cresplanex.nullable.v1.NullableInt64;
import build.buf.gen.cresplanex.nullable.v1.NullableString;

public class NullableMapper {

    public static Integer from(NullableInt32 nullableInt32) {
        return nullableInt32.getHasValue() ? nullableInt32.getValue() : null;
    }

    public static Long from(NullableInt64 nullableInt64) {
        return nullableInt64.getHasValue() ? nullableInt64.getValue() : null;
    }

    public static Float from(NullableFloat nullableFloat) {
        return nullableFloat.getHasValue() ? nullableFloat.getValue() : null;
    }

    public static Double from(NullableDouble nullableDouble) {
        return nullableDouble.getHasValue() ? nullableDouble.getValue() : null;
    }

    public static Boolean from(NullableBool nullableBool) {
        return nullableBool.getHasValue() ? nullableBool.getValue() : null;
    }

    public static byte[] from(NullableBytes nullableBytes) {
        return nullableBytes.getHasValue() ? nullableBytes.getValue().toByteArray() : null;
    }

    public static NullableString toNullable(String s) {
        return NullableString.newBuilder().setHasValue(s != null).setValue(s).build();
    }

    public static NullableInt32 toNullable(Integer i) {
        return NullableInt32.newBuilder().setHasValue(i != null).setValue(i).build();
    }

    public static NullableInt64 toNullable(Long l) {
        return NullableInt64.newBuilder().setHasValue(l != null).setValue(l).build();
    }

    public static NullableFloat toNullable(Float v) {
        return NullableFloat.newBuilder().setHasValue(v != null).setValue(v).build();
    }

    public static NullableDouble toNullable(Double v) {
        return NullableDouble.newBuilder().setHasValue(v != null).setValue(v).build();
    }

    public static NullableBool toNullable(Boolean b) {
        return NullableBool.newBuilder().setHasValue(b != null).setValue(b).build();
    }

    public static NullableBytes toNullable(byte[] bytes) {
        return NullableBytes.newBuilder().setHasValue(bytes != null).setValue(bytes != null ? ByteString.copyFrom(bytes) : null).build();
    }
}
