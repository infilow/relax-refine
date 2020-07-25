package com.infilos.refine.std;

import com.infilos.refine.api.RefineResult;
import com.infilos.refine.api.Refined;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author infilos on 2020-05-16.
 */

public final class Strings {
    public static final Refined<String> IsBlank = new Refined<String>() {
        @Override
        public String name() {
            return "IsBlank";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (StringUtils.isBlank(value)) {
                return RefineResult.succed(name, value, this);
            }

            return RefineResult.failed(name, value, this);
        }
    };
    public static final Refined<String> NotBlank = new Refined<String>() {
        @Override
        public String name() {
            return "NotBlank";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (StringUtils.isNotBlank(value)) {
                return RefineResult.succed(name, value, this);
            }

            return RefineResult.failed(name, value, this);
        }
    };
    public static final Refined<String> HasSpace = new Refined<String>() {
        @Override
        public String name() {
            return "HasSpace";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (Objects.nonNull(value) && value.contains(" ")) {
                return RefineResult.succed(name, value, this);
            }

            return RefineResult.failed(name, value, this);
        }
    };
    public static final Refined<String> Trimable = new Refined<String>() {
        @Override
        public String name() {
            return "Trimable";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (Objects.nonNull(value) && !value.equals(value.trim())) {
                return RefineResult.succed(name, value, this);
            }

            return RefineResult.failed(name, value, this);
        }
    };
    public static Refined<String> IsRegex = new Refined<String>() {
        @Override
        public String name() {
            return "IsRegex";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (StringUtils.isBlank(value)) {
                return RefineResult.failed(name, value, this);
            }

            try {
                Pattern.compile(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsNumber = new Refined<String>() {
        @Override
        public String name() {
            return "IsNumber";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (StringUtils.isNumeric(value)) {
                return RefineResult.succed(name, value, this);
            }

            return RefineResult.failed(name, value, this);
        }
    };
    public static Refined<String> IsDecimal = new Refined<String>() {
        @Override
        public String name() {
            return "IsDecimal";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                new BigDecimal(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsInteger = new Refined<String>() {
        @Override
        public String name() {
            return "IsInteger";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                Integer.parseInt(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsLong = new Refined<String>() {
        @Override
        public String name() {
            return "IsLong";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                Long.parseLong(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsFloat = new Refined<String>() {
        @Override
        public String name() {
            return "IsFloat";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                Float.parseFloat(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsDouble = new Refined<String>() {
        @Override
        public String name() {
            return "IsDouble";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                Double.parseDouble(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsBoolean = new Refined<String>() {
        @Override
        public String name() {
            return "IsBoolean";
        }

        @Override
        public RefineResult check(String name, String value) {
            if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
                return RefineResult.succed(name, value, this);
            }
            return RefineResult.failed(name, value, this);
        }
    };
    public static Refined<String> IsIPAddress = new Refined<String>() {
        @Override
        public String name() {
            return "IsIPAddress";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (InetAddressValidator.getInstance().isValid(value)) {
                return RefineResult.succed(name, value, this);
            } else {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsIpv4 = new Refined<String>() {
        @Override
        public String name() {
            return "IsIpv4";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (InetAddressValidator.getInstance().isValidInet4Address(value)) {
                return RefineResult.succed(name, value, this);
            } else {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsIpv6 = new Refined<String>() {
        @Override
        public String name() {
            return "IsIpv6";
        }

        @Override
        public RefineResult check(String name, String value) {
            if (InetAddressValidator.getInstance().isValidInet6Address(value)) {
                return RefineResult.succed(name, value, this);
            } else {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsJsonObject = new Refined<String>() {
        @Override
        public String name() {
            return "IsJsonObject";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                new JSONObject(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsJsonArray = new Refined<String>() {
        @Override
        public String name() {
            return "IsJsonArray";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                new JSONArray(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsXml = new Refined<String>() {
        @Override
        public String name() {
            return "IsXml";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                builder.parse(new ByteArrayInputStream(value.getBytes()));

                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsUrl = new Refined<String>() {
        @Override
        public String name() {
            return "IsUrl";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                if (UrlValidator.getInstance().isValid(value)) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsDomain = new Refined<String>() {
        @Override
        public String name() {
            return "IsDomain";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                if (DomainValidator.getInstance().isValid(value)) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsHostPort = new Refined<String>() {
        @Override
        public String name() {
            return "IsDomain";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                URL url = new URL("http://" + value);
                if (Objects.isNull(url.getHost()) || url.getPort()==-1) {
                    return RefineResult.failed(name, value, this);
                }

                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsEmail = new Refined<String>() {
        @Override
        public String name() {
            return "IsEmail";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                if (EmailValidator.getInstance().isValid(value)) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsUuid = new Refined<String>() {
        @Override
        public String name() {
            return "IsUuid";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                if (UUID.fromString(value).toString().equals(value)) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };
    public static Refined<String> IsBase64 = new Refined<String>() {
        @Override
        public String name() {
            return "IsBase64";
        }

        @Override
        public RefineResult check(String name, String value) {
            try {
                Base64.getDecoder().decode(value);
                return RefineResult.succed(name, value, this);
            } catch (Throwable ignore) {
                return RefineResult.failed(name, value, this);
            }
        }
    };

    private Strings() {
    }

    public static Refined<String> Contains(String substring) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "Contains";
            }

            @Override
            public Object param() {
                return substring;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.contains(substring)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, value, this);
            }
        };
    }

    public static Refined<String> Endswith(String suffix) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "Endswith";
            }

            @Override
            public Object param() {
                return suffix;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.endsWith(suffix)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, value, this);
            }
        };
    }

    public static Refined<String> Startswith(String prefix) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "Startswith";
            }

            @Override
            public Object param() {
                return prefix;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.startsWith(prefix)) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, value, this);
            }
        };
    }

    public static Refined<String> MatchRegexFully(String regex) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "MatchRegexFully";
            }

            @Override
            public Object param() {
                return regex;
            }

            @Override
            public RefineResult check(String name, String value) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);

                if (matcher.matches()) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, value, this);
            }
        };
    }

    public static Refined<String> MatchRegexRegion(String regex) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "MatchRegexGroup";
            }

            @Override
            public Object param() {
                return regex;
            }

            @Override
            public RefineResult check(String name, String value) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(value);

                if (matcher.find()) {
                    return RefineResult.succed(name, value, this);
                }

                return RefineResult.failed(name, value, this);
            }
        };
    }

    public static Refined<String> IsDatetime(String pattern) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsDatetime";
            }

            @Override
            public Object param() {
                return pattern;
            }

            @Override
            public RefineResult check(String name, String value) {
                try {
                    DateTimeFormatter.ofPattern(pattern).parse(value);
                    return RefineResult.succed(name, value, this);
                } catch (Throwable ignore) {
                    return RefineResult.failed(name, value, this);
                }
            }
        };
    }

    public static Refined<String> IsSizeEqual(int size) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeEqual";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length()==size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> NotSizeEqual(int size) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "NotSizeEqual";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length()!=size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> IsSizeGreaterEqual(int size) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeGreaterEqual";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length() >= size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> IsSizeGreaterThan(int size) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeGreaterThan";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length() > size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> IsSizeLessEqual(int size) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeLessEqual";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length() <= size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> IsSizeLessThan(int size) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeLessThan";
            }

            @Override
            public Object param() {
                return size;
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length() < size) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> IsSizeRangeClose(int min, int max) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeRangeClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length() > min && value.length() < max) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> IsSizeRangeCloseOpen(int min, int max) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeRangeCloseOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length() > min && value.length() <= max) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> IsSizeRangeOpen(int min, int max) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeRangeOpen";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length() >= min && value.length() <= max) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }

    public static Refined<String> IsSizeRangeOpenClose(int min, int max) {
        return new Refined<String>() {
            @Override
            public String name() {
                return "IsSizeRangeOpenClose";
            }

            @Override
            public List<Object> params() {
                return Arrays.asList(min, max);
            }

            @Override
            public RefineResult check(String name, String value) {
                if (Objects.nonNull(value) && value.length() >= min && value.length() < max) {
                    return RefineResult.succed(name, value, this);
                }
                return RefineResult.failed(name, null, this);
            }
        };
    }
}
