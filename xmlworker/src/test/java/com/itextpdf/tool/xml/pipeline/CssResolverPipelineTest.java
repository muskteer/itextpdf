/*
 * $Id$
 *
 * This file is part of the iText (R) project.
 * Copyright (c) 1998-2011 1T3XT BVBA
 * Authors: Balder Van Camp, Emiel Ackermann, et al.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License version 3
 * as published by the Free Software Foundation with the addition of the
 * following permission added to Section 15 as permitted in Section 7(a):
 * FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY 1T3XT,
 * 1T3XT DISCLAIMS THE WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA, 02110-1301 USA, or download the license from the following URL:
 * http://itextpdf.com/terms-of-use/
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License,
 * a covered work must retain the producer line in every PDF that is created
 * or manipulated using iText.
 *
 * You can be released from the requirements of the license by purchasing
 * a commercial license. Buying such a license is mandatory as soon as you
 * develop commercial activities involving the iText software without
 * disclosing the source code of your own applications.
 * These activities include: offering paid services to customers as an ASP,
 * serving PDFs on the fly in a web application, shipping iText with a closed
 * source product.
 *
 * For more information, please contact iText Software Corp. at this
 * address: sales@itextpdf.com
 */
package com.itextpdf.tool.xml.pipeline;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.PipelineException;
import com.itextpdf.tool.xml.Tag;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;

/**
 * Verifies that the CssResolving process is executed by the CssResolverPipeline.
 *
 * @author itextpdf.com
 *
 */
public class CssResolverPipelineTest {

	private Map<String, String> css2;

	@Before
	public void setup() throws CssResolverException, PipelineException {
		StyleAttrCSSResolver css = new StyleAttrCSSResolver();
		css.addCss("dummy { key1: value1; key2: value2 } .aklass { key3: value3;} #dumid { key4: value4}");
		CssResolverPipeline p = new CssResolverPipeline(css, null);
		Tag t = new Tag("dummy");
		t.getAttributes().put("id", "dumid");
		t.getAttributes().put("class", "aklass");
		Pipeline<?> open = p.open(t, null);
		css2 = t.getCSS();
	}

	/**
	 * Verify that pipeline resolves css on tag.
	 *
	 * @throws CssResolverException
	 * @throws PipelineException
	 */
	@Test
	public void verifyCssResolvedTag() throws CssResolverException, PipelineException {
		Assert.assertEquals("value1", css2.get("key1"));
	}

	/**
	 * Verify that pipeline resolves css on tag2.
	 *
	 * @throws CssResolverException
	 * @throws PipelineException
	 */
	@Test
	public void verifyCssResolvedTag2() throws CssResolverException, PipelineException {
		Assert.assertEquals("value2", css2.get("key2"));
	}

	/**
	 * Verify that pipeline resolves css class.
	 *
	 * @throws CssResolverException
	 * @throws PipelineException
	 */
	@Test
	public void verifyCssResolvedClass() throws CssResolverException, PipelineException {
		Assert.assertEquals("value3", css2.get("key3"));
	}

	/**
	 * Verify that pipeline resolves css id.
	 *
	 * @throws CssResolverException
	 * @throws PipelineException
	 */
	@Test
	public void verifyCssResolvedId() throws CssResolverException, PipelineException {
		Assert.assertEquals("value4", css2.get("key4"));
	}

}