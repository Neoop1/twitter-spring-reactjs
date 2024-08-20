## Errors 

Cross-Origin Request Blocked: The Same Origin Policy disallows reading the remote resource at http://twitter.local/ui/v1/user/token. (Reason: CORS header ‘Access-Control-Allow-Origin’ missing). Status code: 504.

import com.fasterxml.jackson.annotation.Json

https://www.baeldung.com/jackson-jsonmappingexception

## File Upload


java.io.FileNotFoundException: MultipartFile resource [file] cannot be resolved to absolute file path
      at org.springframework.core.io.AbstractResource.getFile(AbstractResource.java:138) ~[spring-core-5.3.24.jar!/:5.3.24]
      at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
      at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) ~[na:na]
      at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
      at java.base/java.lang.reflect.Method.invoke(Method.java:568) ~[na:na]
      at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:689) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:728) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:774) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.MapSerializer.serializeFields(MapSerializer.java:808) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.MapSerializer.serializeWithoutTypeInfo(MapSerializer.java:764) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.MapSerializer.serialize(MapSerializer.java:720) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.std.MapSerializer.serialize(MapSerializer.java:35) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:480) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:400) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ObjectWriter$Prefetch.serialize(ObjectWriter.java:1514) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at com.fasterxml.jackson.databind.ObjectWriter.writeValue(ObjectWriter.java:1007) ~[jackson-databind-2.13.4.2.jar!/:2.13.4.2]
      at org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter.writeInternal(AbstractJackson2HttpMessageConverter.java:456) ~[spring-web-5.3.24.jar!/:5.3.24]
      at org.springframework.http.converter.AbstractGenericHttpMessageConverter.write(AbstractGenericHttpMessageConverter.java:104) ~[spring-web-5.3.24.jar!/:5.3.24]
      at org.springframework.cloud.openfeign.support.SpringEncoder.checkAndWrite(SpringEncoder.java:217) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at org.springframework.cloud.openfeign.support.SpringEncoder.encodeWithMessageConverter(SpringEncoder.java:146) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at org.springframework.cloud.openfeign.support.SpringEncoder.encode(SpringEncoder.java:134) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at org.springframework.cloud.openfeign.support.PageableSpringEncoder.encode(PageableSpringEncoder.java:101) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at feign.ReflectiveFeign$BuildFormEncodedTemplateFromArgs.resolve(ReflectiveFeign.java:358) ~[feign-core-11.7.jar!/:na]
      at feign.ReflectiveFeign$BuildTemplateByResolvingArgs.create(ReflectiveFeign.java:232) ~[feign-core-11.7.jar!/:na]
      at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:84) ~[feign-core-11.7.jar!/:na]
      at org.springframework.cloud.openfeign.FeignCircuitBreakerInvocationHandler.lambda$asSupplier$1(FeignCircuitBreakerInvocationHandler.java:112) ~[spring-cloud-openfeign-core-3.1.0.jar!/:3.1.0]
      at org.springframework.cloud.sleuth.instrument.circuitbreaker.TraceSupplier.get(TraceSupplier.java:52) ~[spring-cloud-sleuth-instrumentation-3.1.0.jar!/:3.1.0]
      at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
      at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
      at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
      at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
tweet-service  | 

                
