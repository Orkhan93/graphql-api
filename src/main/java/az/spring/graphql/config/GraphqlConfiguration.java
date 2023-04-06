package az.spring.graphql.config;

import graphql.scalars.ExtendedScalars;
import graphql.schema.idl.RuntimeWiring;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphqlConfiguration {

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return new RuntimeWiringConfigurer() {
            @Override
            public void configure(RuntimeWiring.Builder builder) {
                builder.scalar(ExtendedScalars.DateTime);
            }
        };
    }

}