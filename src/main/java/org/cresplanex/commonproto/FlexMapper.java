package org.cresplanex.commonproto;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.protobuf.ByteString;

import build.buf.gen.cresplanex.flex.v1.Flex;
import build.buf.gen.cresplanex.flex.v1.FlexArray;
import build.buf.gen.cresplanex.flex.v1.FlexMap;
import build.buf.gen.cresplanex.flex.v1.NullableFlex;
import build.buf.gen.cresplanex.flex.v1.NullableFlexArray;
import build.buf.gen.cresplanex.flex.v1.NullableFlexMap;
import build.buf.gen.cresplanex.nullable.v1.NullableBool;
import build.buf.gen.cresplanex.nullable.v1.NullableBytes;
import build.buf.gen.cresplanex.nullable.v1.NullableDouble;
import build.buf.gen.cresplanex.nullable.v1.NullableFloat;
import build.buf.gen.cresplanex.nullable.v1.NullableInt32;
import build.buf.gen.cresplanex.nullable.v1.NullableInt64;
import build.buf.gen.cresplanex.nullable.v1.NullableString;

public class FlexMapper {

    public static Object from(Flex flex) {
        if (flex == null) {
            return null;
        }
        return switch (flex.getFlexCase()) {
            case STRING_VALUE -> flex.getStringValue();
            case INT_VALUE -> flex.getIntValue();
            case LONG_VALUE -> flex.getLongValue();
            case FLOAT_VALUE -> flex.getFloatValue();
            case DOUBLE_VALUE -> flex.getDoubleValue();
            case BOOL_VALUE -> flex.getBoolValue();
            case BYTES_VALUE -> flex.getBytesValue().toByteArray();
            case FLEX_VALUE -> from(flex.getFlexValue());
            case ARRAY_VALUE -> flex.getArrayValue().getFlexList().stream()
                    .map(FlexMapper::from)
                    .collect(Collectors.toList());
            case MAP_VALUE -> flex.getMapValue().getFlexMap().entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> from(entry.getValue())
                    ));
            case NULLABLE_STRING_VALUE -> flex.getNullableStringValue().getHasValue()
                    ? flex.getNullableStringValue().getValue()
                    : null;
            case NULLABLE_INT_VALUE -> flex.getNullableIntValue().getHasValue()
                    ? flex.getNullableIntValue().getValue()
                    : null;
            case NULLABLE_LONG_VALUE -> flex.getNullableLongValue().getHasValue()
                    ? flex.getNullableLongValue().getValue()
                    : null;
            case NULLABLE_FLOAT_VALUE -> flex.getNullableFloatValue().getHasValue()
                    ? flex.getNullableFloatValue().getValue()
                    : null;
            case NULLABLE_DOUBLE_VALUE -> flex.getNullableDoubleValue().getHasValue()
                    ? flex.getNullableDoubleValue().getValue()
                    : null;
            case NULLABLE_BOOL_VALUE -> flex.getNullableBoolValue().getHasValue()
                    ? flex.getNullableBoolValue().getValue()
                    : null;
            case NULLABLE_BYTES_VALUE -> flex.getNullableBytesValue().getHasValue()
                    ? flex.getNullableBytesValue().getValue().toByteArray()
                    : null;
            case NULLABLE_FLEX_VALUE -> flex.getNullableFlexValue().getHasValue()
                    ? from(flex.getNullableFlexValue().getValue())
                    : null;
            case NULLABLE_ARRAY_VALUE -> flex.getNullableArrayValue().getHasValue()
                    ? flex.getNullableArrayValue().getValue().getFlexList().stream()
                    .map(FlexMapper::from)
                    .collect(Collectors.toList())
                    : null;
            case NULLABLE_MAP_VALUE -> flex.getNullableMapValue().getHasValue()
                    ? flex.getNullableMapValue().getValue().getFlexMap().entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> from(entry.getValue())
                    ))
                    : null;
            default -> null;
        };
    }

    public static List<Object> from(FlexArray flexArray) {
        return flexArray.getFlexList().stream()
                .map(FlexMapper::from)
                .collect(Collectors.toList());
    }

    public static Map<String, Object> from(FlexMap flexMap) {
        return flexMap.getFlexMap().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> from(entry.getValue())
                ));
    }

    public static Object from(NullableFlex nullableFlex) {
        return nullableFlex.getHasValue() ? from(nullableFlex.getValue()) : null;
    }

    public static List<Object> from(NullableFlexArray nullableFlexArray) {
        return nullableFlexArray.getHasValue() ? nullableFlexArray.getValue().getFlexList().stream()
                .map(FlexMapper::from)
                .collect(Collectors.toList()) : null;
    }

    public static Map<String, Object> from(NullableFlexMap nullableFlexMap) {
        return nullableFlexMap.getHasValue() ? nullableFlexMap.getValue().getFlexMap().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> from(entry.getValue())
                )) : null;
    }

    public static Flex to(Object obj) {
        Flex.Builder flexBuilder = Flex.newBuilder();
        switch (obj) {
            case null:
                flexBuilder.setNullableFlexValue(toNullable((Object)null));
                break;
            case String s:
                flexBuilder.setNullableStringValue(NullableString.newBuilder().setHasValue(true).setValue(s).build());
                break;
            case Integer i:
                flexBuilder.setNullableIntValue(NullableInt32.newBuilder().setHasValue(true).setValue(i).build());
                break;
            case Long l:
                flexBuilder.setNullableLongValue(NullableInt64.newBuilder().setHasValue(true).setValue(l).build());
                break;
            case Float v:
                flexBuilder.setNullableFloatValue(NullableFloat.newBuilder().setHasValue(true).setValue(v).build());
                break;
            case Double v:
                flexBuilder.setNullableDoubleValue(NullableDouble.newBuilder().setHasValue(true).setValue(v).build());
                break;
            case Boolean b:
                flexBuilder.setNullableBoolValue(NullableBool.newBuilder().setHasValue(true).setValue(b).build());
                break;
            case byte[] bytes:
                flexBuilder.setNullableBytesValue(NullableBytes.newBuilder().setHasValue(true).setValue(ByteString.copyFrom(bytes)).build());
                break;
            case Flex flex:
                flexBuilder.setNullableFlexValue(toNullable(flex));
                break;
            case List<?> objects:
                FlexArray.Builder arrayBuilder = FlexArray.newBuilder();
                Iterator<?> var20 = objects.iterator();

                while(var20.hasNext()) {
                    Object element = var20.next();
                    arrayBuilder.addFlex(to(element));
                }

                flexBuilder.setNullableArrayValue(NullableFlexArray.newBuilder().setHasValue(true).setValue(arrayBuilder.build()).build());
                break;
            case Map<?, ?> map:
                FlexMap.Builder mapBuilder = FlexMap.newBuilder();
                Iterator<?> var15 = map.entrySet().iterator();

                while(var15.hasNext()) {
                    Map.Entry<?, ?> entry = (Map.Entry<?, ?>)var15.next();
                    Object var18 = entry.getKey();
                    if (!(var18 instanceof String)) {
                        throw new IllegalArgumentException("Map keys must be of type String");
                    }

                    String key = (String)var18;
                    mapBuilder.putFlex(key, to(entry.getValue()));
                }

                flexBuilder.setNullableMapValue(NullableFlexMap.newBuilder().setHasValue(true).setValue(mapBuilder.build()).build());
                break;
            default:
                flexBuilder.setNullableFlexValue(toNullable(obj));
        }

        return flexBuilder.build();
    }

    public static NullableFlex toNullable(Object obj) {
        NullableFlex.Builder nullableFlexBuilder = NullableFlex.newBuilder();
        if (obj == null) {
            nullableFlexBuilder.setHasValue(false);
        } else {
            nullableFlexBuilder.setHasValue(true);
            nullableFlexBuilder.setValue(to(obj));
        }

        return nullableFlexBuilder.build();
    }
}
