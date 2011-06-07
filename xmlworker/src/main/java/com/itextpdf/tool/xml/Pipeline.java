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
package com.itextpdf.tool.xml;

/**
 * @author redlab_b
 * @param <T> the type of CustomContext
 *
 */
public interface Pipeline<T extends CustomContext> {

	/**
	 * Sets the global worker context.
	 * @param context the worker context
	 */
	public void setContext(WorkerContext context);

	/**
	 * Called when an opening tag has been encountered.
	 * @param t the Tag
	 * @param po a processObject to put {@link Writable}s in
	 * @return the next pipeline in line
	 * @throws PipelineException can be thrown to indicate that something went wrong.
	 */
	public Pipeline<?> open(Tag t, ProcessObject po) throws PipelineException;

	/**
	 * Called when content has been encountered.
	 * @param t the Tag
	 * @param content the content
	 * @param po a processObject to put {@link Writable}s in
	 * @return the next pipeline in line
	 * @throws PipelineException can be thrown to indicate that something went wrong.
	 */
	public Pipeline<?> content(Tag t, byte[] content, ProcessObject po) throws PipelineException;

	/**
	 * Called when a closing tag has been encountered.
	 * @param t the Tag
	 * @param po a processObject to put {@link Writable}s in
	 * @return the next pipeline in line
	 * @throws PipelineException  can be thrown to indicate that something went wrong.
	 */
	public Pipeline<?> close(Tag t, ProcessObject po) throws PipelineException;

	/**
	 * Returns the next pipeline in line.
	 * @return the next pipeline
	 */
	public Pipeline<?> getNext();

	/**
	 * @return a <strong>new</strong> custom context for this pipeline
	 * @throws NoCustomContextException if there is no custom context for this pipeline
	 */
	public T getNewCustomContext() throws NoCustomContextException;

	/**
	 * @return the local context
	 * @throws PipelineException if there is no custom context
	 */
	T getLocalContext() throws PipelineException;

	/**
	 * @return a key to use as key for the context
	 */
	public String getContextKey();
}