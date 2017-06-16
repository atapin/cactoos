/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.cactoos.Input;
import org.cactoos.text.BytesAsText;
import org.w3c.dom.ls.LSInput;

// @checkstyle AbbreviationAsWordInNameCheck (10 lines)
/**
 * Input as LSInput.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.6
 */
public final class InputAsLSInput implements LSInput {

    /**
     * The input.
     */
    private final Input input;

    /**
     * PublicID.
     */
    private final String pid;

    /**
     * SystemID.
     */
    private final String sid;

    /**
     * Base.
     */
    private final String base;

    /**
     * Ctor.
     * @param inpt Input
     */
    public InputAsLSInput(final Input inpt) {
        this(inpt, "#public", "#system", "#base");
    }

    // @checkstyle ParameterNumberCheck (10 lines)
    /**
     * Ctor.
     * @param inpt Input
     * @param pubid PublicID
     * @param sysid SystemID
     * @param bse Base
     */
    public InputAsLSInput(final Input inpt, final String pubid,
        final String sysid, final String bse) {
        this.input = inpt;
        this.pid = pubid;
        this.sid = sysid;
        this.base = bse;
    }

    @Override
    public Reader getCharacterStream() {
        return new InputStreamReader(this.getByteStream());
    }

    @Override
    public void setCharacterStream(final Reader stream) {
        throw new UnsupportedOperationException(
            "#setCharacterStream() is not supported"
        );
    }

    @Override
    public InputStream getByteStream() {
        try {
            return this.input.stream();
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void setByteStream(final InputStream stream) {
        throw new UnsupportedOperationException(
            "#setByteStream() is not supported"
        );
    }

    @Override
    public String getStringData() {
        try {
            return new BytesAsText(new InputAsBytes(this.input)).asString();
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void setStringData(final String data) {
        throw new UnsupportedOperationException(
            "#setStringData() is not supported"
        );
    }

    @Override
    public String getSystemId() {
        return this.sid;
    }

    @Override
    public void setSystemId(final String sysid) {
        throw new UnsupportedOperationException(
            "#setSystemId() is not supported"
        );
    }

    @Override
    public String getPublicId() {
        return this.pid;
    }

    @Override
    public void setPublicId(final String pubid) {
        throw new UnsupportedOperationException(
            "#setPublicId() is not supported"
        );
    }

    @Override
    public String getBaseURI() {
        return this.base;
    }

    @Override
    public void setBaseURI(final String uri) {
        throw new UnsupportedOperationException(
            "#setBaseURI() is not supported"
        );
    }

    @Override
    public String getEncoding() {
        return StandardCharsets.UTF_8.displayName();
    }

    @Override
    public void setEncoding(final String encoding) {
        throw new UnsupportedOperationException(
            "#setEncoding() is not supported"
        );
    }

    @Override
    @SuppressWarnings("PMD.BooleanGetMethodName")
    public boolean getCertifiedText() {
        return true;
    }

    @Override
    public void setCertifiedText(final boolean text) {
        throw new UnsupportedOperationException(
            "#setCertifiedText() is not supported"
        );
    }
}
