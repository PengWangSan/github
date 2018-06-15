/*
 *
 * Copyright 2017-2018 549477611@qq.com(xiaoyu)
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.ecoo.dtx.admin.msg;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionActor;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoyu
 */
@SuppressWarnings("unchecked")
public class ProtostuffSerializer {
	private static final Objenesis OBJENESIS_STD = new ObjenesisStd(true);


	/**
	 * 序列化对象
	 *
	 * @param obj
	 *            需要序更列化的对象
	 * @return byte []
	 * @throws MythException
	 *             异常信息
	 */
	public static byte[] serialize(Object obj) {
		Class cls = obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			Schema schema = RuntimeSchema.createFrom(cls);
			ProtostuffIOUtil.writeTo(outputStream, obj, schema, buffer);
		} catch (Exception e) {
		} finally {
			buffer.clear();
		}
		return outputStream.toByteArray();
	}

	public static <T> T deSerialize(byte[] param, Class<T> clazz)  {
		T object=null;
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(param);
			Class cls = clazz;
			object = OBJENESIS_STD.newInstance((Class<T>) cls);
			Schema schema = RuntimeSchema.createFrom(cls);
			ProtostuffIOUtil.mergeFrom(inputStream, object, schema);
			return object;
		} catch (Exception e) {
		}
		return object;
	}
	
	public static void main(String[] ars) throws IOException {

		DtxTransaction test = new DtxTransaction();
		test.setClassName("sdf");
		
		List<DtxTransactionActor> acotors = new ArrayList<DtxTransactionActor>();
		for(int i=0;i<=10;i++) {
		DtxTransactionActor actor = new DtxTransactionActor();
		acotors.add(actor);
		}
		test.setTransactionActors(acotors);

		byte[] param = serialize(test);

		DtxTransaction tt = deSerialize(param, DtxTransaction.class);

		for (DtxTransactionActor a : tt.getTransactionActors()) {
			System.out.println(a.getClassName());
		}

	}

}
