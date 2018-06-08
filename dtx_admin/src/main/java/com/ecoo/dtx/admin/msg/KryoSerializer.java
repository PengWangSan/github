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

import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionActor;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KryoSerializer {
	/**
	 * 序列化
	 *
	 * @param obj
	 *            需要序更列化的对象
	 * @return 序列化后的byte 数组
	 * @throws IOException
	 * @throws MythException
	 *             异常
	 */
	public static byte[] serialize(Object obj,Class...refenceClass) throws IOException {
		byte[] bytes;
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// 获取kryo对象
		Kryo kryo = new Kryo();

		kryo.register(obj.getClass());
		for(Class classT:refenceClass) {
			kryo.register(classT);
		}
		Output output = new Output(outputStream);
		kryo.writeObject(output, obj);
		bytes = output.toBytes();
		output.flush();
		output.close();
		outputStream.close();
		return bytes;
	}

	/**
	 * 反序列化
	 *
	 * @param param
	 *            需要反序列化的byte []
	 * @return 序列化对象
	 * @throws IOException
	 * @throws MythException
	 *             异常
	 */
	public static <T> T deSerialize(byte[] param, Class<T> clazz,Class...refenceClass) throws IOException {
		T object;
		ByteArrayInputStream inputStream = new ByteArrayInputStream(param);
		Kryo kryo = new Kryo();
		kryo.register(clazz);
		for(Class classT:refenceClass) {
			kryo.register(classT);
		}
		Input input = new Input(inputStream);
		object = kryo.readObject(input, clazz);
		input.close();
		inputStream.close();
		return object;
	}

	public static void main(String[] ars) throws IOException {

		DtxTransaction test = new DtxTransaction();
		test.setClassName("sdf");
		DtxTransactionActor actor = new DtxTransactionActor();
		List<DtxTransactionActor> acotors = new ArrayList<DtxTransactionActor>();
		acotors.add(actor);
		test.setTransactionActors(acotors);

		byte[] param = serialize(test);

		DtxTransaction tt = deSerialize(param, DtxTransaction.class);

		for (DtxTransactionActor a : tt.getTransactionActors()) {
			System.out.println(a.getMethod());
		}

	}

}
