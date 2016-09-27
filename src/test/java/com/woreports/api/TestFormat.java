package com.woreports.api;

import static com.woreports.api.Format.PDF;
import static com.woreports.api.Format.XLSX;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
@RunWith(Parameterized.class)
public class TestFormat {
    @Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { PDF, "application/pdf" }, { XLSX, "application/vnd.ms-excel" } });
    }

    private final Format format;

    private final String mimeType;

    public TestFormat(Format format, String mimeType) {
        this.format = format;
        this.mimeType = mimeType;
    }

    @Test
    public void contentTypeForFormat() throws Exception {
        assertThat(format.mimeType(), is(mimeType));
    }
}
