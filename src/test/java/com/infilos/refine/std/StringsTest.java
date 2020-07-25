package com.infilos.refine.std;

import com.infilos.refine.Refine;
import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.assertTrue;

/**
 * @author zhiguang.zhang on 2020-07-25.
 */

public class StringsTest {

    @Test
    public void test() {
        assertTrue(Refine.of("check").expect("").match(Strings.IsBlank).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Strings.IsBlank).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.NotBlank).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.NotBlank).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.Contains("a")).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.Contains("a")).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.Endswith("a")).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.Endswith("a")).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.Startswith("a")).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.Startswith("a")).check().isFailed());

        assertTrue(Refine.of("check").expect(" ").match(Strings.HasSpace).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.HasSpace).check().isFailed());

        assertTrue(Refine.of("check").expect(" ").match(Strings.Trimable).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.Trimable).check().isFailed());

        assertTrue(Refine.of("check").expect("(\\d+)").match(Strings.IsRegex).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.IsRegex).check().isFailed());

        assertTrue(Refine.of("check").expect("12").match(Strings.MatchRegexFully("(\\d+)")).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.MatchRegexFully("(\\d+)")).check().isFailed());

        assertTrue(Refine.of("check").expect("a12a").match(Strings.MatchRegexRegion("(\\d+)")).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.MatchRegexRegion("(\\d+)")).check().isFailed());

        assertTrue(Refine.of("check").expect("12").match(Strings.IsNumber).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.IsNumber).check().isFailed());

        assertTrue(Refine.of("check").expect("12").match(Strings.IsDecimal).check().isSucced());
        assertTrue(Refine.of("check").expect("12.22").match(Strings.IsDecimal).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.IsDecimal).check().isFailed());

        assertTrue(Refine.of("check").expect("12").match(Strings.IsInteger).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.IsInteger).check().isFailed());

        assertTrue(Refine.of("check").expect("12").match(Strings.IsLong).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.IsLong).check().isFailed());

        assertTrue(Refine.of("check").expect("12").match(Strings.IsFloat).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.IsFloat).check().isFailed());

        assertTrue(Refine.of("check").expect("12").match(Strings.IsDouble).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.IsDouble).check().isFailed());

        assertTrue(Refine.of("check").expect("true").match(Strings.IsBoolean).check().isSucced());
        assertTrue(Refine.of("check").expect("aa").match(Strings.IsBoolean).check().isFailed());

        assertTrue(Refine.of("check").expect("2020-12-11").match(Strings.IsDatetime("yyyy-MM-dd")).check().isSucced());
        assertTrue(Refine.of("check").expect("2020-12-11 12:23:34").match(Strings.IsDatetime("yyyy-MM-dd HH:mm:ss")).check().isSucced());
        assertTrue(Refine.of("check").expect("2020-12-11").match(Strings.IsDatetime("yyyy-MM-dd HH:mm:ss")).check().isFailed());

        assertTrue(Refine.of("check").expect("1.1.1.1").match(Strings.IsIPAddress).check().isSucced());
        assertTrue(Refine.of("check").expect("1.1.1").match(Strings.IsIPAddress).check().isFailed());

        assertTrue(Refine.of("check").expect("1.1.1.1").match(Strings.IsIpv4).check().isSucced());
        assertTrue(Refine.of("check").expect("1.1.1").match(Strings.IsIpv4).check().isFailed());

        assertTrue(Refine.of("check").expect("2001:db8::1:0:0:1").match(Strings.IsIpv6).check().isSucced());
        assertTrue(Refine.of("check").expect("1.1.1").match(Strings.IsIpv6).check().isFailed());

        assertTrue(Refine.of("check").expect("{}").match(Strings.IsJsonObject).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.IsJsonObject).check().isFailed());

        assertTrue(Refine.of("check").expect("[]").match(Strings.IsJsonArray).check().isSucced());
        assertTrue(Refine.of("check").expect("").match(Strings.IsJsonArray).check().isFailed());

        assertTrue(Refine.of("check").expect("<project>name</project>").match(Strings.IsXml).check().isSucced());
        assertTrue(Refine.of("check").expect("<project>").match(Strings.IsXml).check().isFailed());

        assertTrue(Refine.of("check").expect("https://www.google.com:80").match(Strings.IsUrl).check().isSucced());
        assertTrue(Refine.of("check").expect("https://www").match(Strings.IsUrl).check().isFailed());

        assertTrue(Refine.of("check").expect("www.google.com").match(Strings.IsDomain).check().isSucced());
        assertTrue(Refine.of("check").expect("google.com").match(Strings.IsDomain).check().isSucced());
        assertTrue(Refine.of("check").expect("google").match(Strings.IsDomain).check().isFailed());

        assertTrue(Refine.of("check").expect("8.8.8.8:80").match(Strings.IsHostPort).check().isSucced());
        assertTrue(Refine.of("check").expect("8.8.8.8").match(Strings.IsHostPort).check().isFailed());
        assertTrue(Refine.of("check").expect("google").match(Strings.IsHostPort).check().isFailed());

        assertTrue(Refine.of("check").expect("infilos00@gmail.com").match(Strings.IsEmail).check().isSucced());
        assertTrue(Refine.of("check").expect("8.8.8.8").match(Strings.IsEmail).check().isFailed());

        assertTrue(Refine.of("check").expect("0297bba3-910e-6672-89e8-c3eacecfe1fa").match(Strings.IsUuid).check().isSucced());
        assertTrue(Refine.of("check").expect("000-000-000-0-0").match(Strings.IsUuid).check().isFailed());
        assertTrue(Refine.of("check").expect("1-1-1-1-1").match(Strings.IsUuid).check().isFailed());
        assertTrue(Refine.of("check").expect("1-1-1-1-1-1").match(Strings.IsUuid).check().isFailed());
        assertTrue(Refine.of("check").expect("0-0-0-0").match(Strings.IsUuid).check().isFailed());
        assertTrue(Refine.of("check").expect("a-a-a-a-a").match(Strings.IsUuid).check().isFailed());
        assertTrue(Refine.of("check").expect("#-b-c-4-3").match(Strings.IsUuid).check().isFailed());

        assertTrue(Refine.of("check").expect(new String(Base64.getEncoder().encode("a.b.c".getBytes()))).match(Strings.IsBase64).check().isSucced());
        assertTrue(Refine.of("check").expect("a.b.c").match(Strings.IsBase64).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeEqual(1)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeEqual(1)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.NotSizeEqual(2)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.NotSizeEqual(2)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeGreaterEqual(1)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeGreaterEqual(3)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeGreaterThan(0)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeGreaterThan(3)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeLessEqual(1)).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeLessEqual(2)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeLessEqual(1)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeLessThan(2)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeLessThan(2)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeRangeClose(0, 2)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeRangeClose(0, 2)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeRangeCloseOpen(0, 2)).check().isSucced());
        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeRangeCloseOpen(0, 1)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeRangeCloseOpen(0, 1)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeRangeOpen(0, 1)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeRangeOpen(0, 1)).check().isFailed());

        assertTrue(Refine.of("check").expect("a").match(Strings.IsSizeRangeOpenClose(0, 2)).check().isSucced());
        assertTrue(Refine.of("check").expect("ab").match(Strings.IsSizeRangeOpenClose(0, 2)).check().isFailed());
    }

}