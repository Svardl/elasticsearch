/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.search;

import org.apache.lucene.util.BytesRef;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.io.stream.Writeable;
import org.elasticsearch.common.xcontent.ToXContent.Params;
import org.elasticsearch.common.xcontent.ToXContentFragment;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.XContentParserUtils;
import org.elasticsearch.search.SearchHit.Fields;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import java.io.File;
import java.io.PrintWriter;

public class SearchSortValues implements ToXContentFragment, Writeable {

    static final SearchSortValues EMPTY = new SearchSortValues(new Object[0]);
    private final Object[] sortValues;

    SearchSortValues(Object[] sortValues) {
        this.sortValues = Objects.requireNonNull(sortValues, "sort values must not be empty");
    }

    public SearchSortValues(Object[] sortValues, DocValueFormat[] sortValueFormats) {
        Objects.requireNonNull(sortValues);
        Objects.requireNonNull(sortValueFormats);
        this.sortValues = Arrays.copyOf(sortValues, sortValues.length);
        for (int i = 0; i < sortValues.length; ++i) {
            if (this.sortValues[i] instanceof BytesRef) {
                this.sortValues[i] = sortValueFormats[i].format((BytesRef) sortValues[i]);
            }
        }
    }

    public SearchSortValues(StreamInput in) throws IOException {
        File file = new File("/Users/RyanAFinley/projects/scripts-elasticsearch/coverage.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.println("SearchSortValues");
        writer.close();
        int size = in.readVInt();
        if (size > 0) {
            sortValues = new Object[size];
            for (int i = 0; i < sortValues.length; i++) {
                byte type = in.readByte();
                if (type == 0) {
                    PrintWriter writer1 = new PrintWriter(file);
                    writer1.println("s1");
                    writer1.close();
                    sortValues[i] = null;
                } else if (type == 1) {
                    PrintWriter writer2 = new PrintWriter(file);
                    writer2.println("s2");
                    writer2.close();
                    sortValues[i] = in.readString();
                } else if (type == 2) {
                    PrintWriter writer3 = new PrintWriter(file);
                    writer3.println("s3");
                    writer3.close();
                    sortValues[i] = in.readInt();
                } else if (type == 3) {
                    PrintWriter writer4 = new PrintWriter(file);
                    writer4.println("s4");
                    writer4.close();
                    sortValues[i] = in.readLong();
                } else if (type == 4) {
                    PrintWriter writer5 = new PrintWriter(file);
                    writer5.println("s5");
                    writer5.close();
                    sortValues[i] = in.readFloat();
                } else if (type == 5) {
                    PrintWriter writer6 = new PrintWriter(file);
                    writer6.println("s6");
                    writer6.close();
                    sortValues[i] = in.readDouble();
                } else if (type == 6) {
                    PrintWriter writer7 = new PrintWriter(file);
                    writer7.println("s7");
                    writer7.close();
                    sortValues[i] = in.readByte();
                } else if (type == 7) {
                    PrintWriter writer8 = new PrintWriter(file);
                    writer8.println("s8");
                    writer8.close();
                    sortValues[i] = in.readShort();
                } else if (type == 8) {
                    PrintWriter writer9 = new PrintWriter(file);
                    writer9.println("s9");
                    writer9.close();
                    sortValues[i] = in.readBoolean();
                } else {
                    PrintWriter writer10 = new PrintWriter(file);
                    writer10.println("s10");
                    writer10.close();
                    throw new IOException("Can't match type [" + type + "]");
                }
            }
        } else {
            PrintWriter writer11 = new PrintWriter(file);
            writer11.println("s11");
            writer11.close();
            sortValues = new Object[0];
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        File file = new File("/Users/RyanAFinley/projects/scripts-elasticsearch/coverage.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.println("writeTo");
        writer.close();
        out.writeVInt(sortValues.length);
        for (Object sortValue : sortValues) {
            if (sortValue == null) {
                PrintWriter writer1 = new PrintWriter(file);
                writer1.println("w1");
                writer1.close();
                out.writeByte((byte) 0);
            } else {
                Class type = sortValue.getClass();
                if (type == String.class) {
                    PrintWriter writer2 = new PrintWriter(file);
                    writer2.println("w2");
                    writer2.close();
                    out.writeByte((byte) 1);
                    out.writeString((String) sortValue);
                } else if (type == Integer.class) {
                    PrintWriter writer3 = new PrintWriter(file);
                    writer3.println("w3");
                    writer3.close();
                    out.writeByte((byte) 2);
                    out.writeInt((Integer) sortValue);
                } else if (type == Long.class) {
                    PrintWriter writer4 = new PrintWriter(file);
                    writer4.println("w4");
                    writer4.close();
                    out.writeByte((byte) 3);
                    out.writeLong((Long) sortValue);
                } else if (type == Float.class) {
                    PrintWriter writer5 = new PrintWriter(file);
                    writer5.println("w5");
                    writer5.close();
                    out.writeByte((byte) 4);
                    out.writeFloat((Float) sortValue);
                } else if (type == Double.class) {
                    PrintWriter writer6 = new PrintWriter(file);
                    writer6.println("w6");
                    writer6.close();
                    out.writeByte((byte) 5);
                    out.writeDouble((Double) sortValue);
                } else if (type == Byte.class) {
                    PrintWriter writer7 = new PrintWriter(file);
                    writer7.println("w7");
                    writer7.close();
                    out.writeByte((byte) 6);
                    out.writeByte((Byte) sortValue);
                } else if (type == Short.class) {
                    PrintWriter writer8 = new PrintWriter(file);
                    writer8.println("w8");
                    writer8.close();
                    out.writeByte((byte) 7);
                    out.writeShort((Short) sortValue);
                } else if (type == Boolean.class) {
                    PrintWriter writer9 = new PrintWriter(file);
                    writer9.println("w9");
                    writer9.close();
                    out.writeByte((byte) 8);
                    out.writeBoolean((Boolean) sortValue);
                } else {
                    PrintWriter writer10 = new PrintWriter(file);
                    writer10.println("w10");
                    writer.close();
                    throw new IOException("Can't handle sort field value of type [" + type + "]");
                }
            }
        }
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        if (sortValues.length > 0) {
            builder.startArray(Fields.SORT);
            for (Object sortValue : sortValues) {
                builder.value(sortValue);
            }
            builder.endArray();
        }
        return builder;
    }

    public static SearchSortValues fromXContent(XContentParser parser) throws IOException {
        XContentParserUtils.ensureExpectedToken(XContentParser.Token.START_ARRAY, parser.currentToken(), parser::getTokenLocation);
        return new SearchSortValues(parser.list().toArray());
    }

    public Object[] sortValues() {
        return sortValues;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SearchSortValues other = (SearchSortValues) obj;
        return Arrays.equals(sortValues, other.sortValues);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(sortValues);
    }
}
