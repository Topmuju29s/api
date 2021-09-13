package ws.license.exam.util;

import java.util.Objects;

public class StringCommon {

	public static String ConvertNullToEmptyString(Object value) {
        String stringValue = Objects.toString(value, "");;
        stringValue = (("null".equals(stringValue)) ? "" : stringValue);
        System.out.println("stringValue = " + stringValue);
        return stringValue;
    }

}