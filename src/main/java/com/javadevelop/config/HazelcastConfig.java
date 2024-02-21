package com.javadevelop.config;



//@Configuration
//@EnableHazelcastHttpSession
public class HazelcastConfig {

//    @Bean
//    public HazelcastInstance hazelcastInstance() {
//        Config config = new Config();
//        MapAttributeConfig attributeConfig = new MapAttributeConfig()
//                .setName(HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
//                .setExtractor(PrincipalNameExtractor.class.getName());
//        config.getMapConfig(HazelcastIndexedSessionRepository.DEFAULT_SESSION_MAP_NAME)
//                .addMapAttributeConfig(attributeConfig).addMapIndexConfig(
//                new MapIndexConfig(HazelcastIndexedSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));
//        SerializerConfig serializerConfig = new SerializerConfig();
//        serializerConfig.setImplementation(new HazelcastSessionSerializer()).setTypeClass(MapSession.class);
//        config.getSerializationConfig().addSerializerConfig(serializerConfig);
//        return Hazelcast.newHazelcastInstance(config);
//    }
}
