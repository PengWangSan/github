package com.ecoo.dtx.admin.config;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import com.ecoo.dtx.admin.msg.KryoSerializer;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionActor;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RedisConfig<br>
 * http://www.jianshu.com/p/a78de210a947
 * http://www.ityouknow.com/springboot/2016/03/06/springboot(%E4%B8%89)-Spring-Boot%E4%B8%ADRedis%E7%9A%84%E4%BD%BF%E7%94%A8.html
 * 
 * @author Dave.zhao
 * @version [版本号, Jun 12, 2017]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Configuration
public class RedisConfig {

	// @Bean
	// JedisConnectionFactory jedisConnectionFactory() {
	// return new JedisConnectionFactory();
	// }

	@Bean
	public RedisTemplate<String, DtxTransaction> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, DtxTransaction> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);

		template.setKeySerializer(new StringRedisSerializer());

		KryoRedisSerializer<DtxTransaction>  KryoRedisSerializer = new KryoRedisSerializer<DtxTransaction>(DtxTransaction.class,new Class[] {DtxTransactionActor.class});
		template.setValueSerializer(KryoRedisSerializer);

		return template;
	}

	public class KryoRedisSerializer<T> implements RedisSerializer<T> {

		
		private final Class<T> type;
		
		private final Class[] refClass;
		
		public KryoRedisSerializer(Class<T> type,Class[] refClass) {

			Assert.notNull(type, "Type must not be null!");

			this.type = type;
			
			this.refClass=refClass;
		}
		
		@Override
		public byte[] serialize(T t) throws SerializationException {
			return null;
		}

		@Override
		public T deserialize(byte[] bytes) throws SerializationException {
			
			
			try {
				return KryoSerializer.deSerialize(bytes, type,refClass);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}






	}
}
