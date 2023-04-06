package az.spring.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphqlException extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof UserNotFoundException) {
            return toGraphqlException(ex);
        } else if (ex instanceof Exception) {
            return toGraphqlException(ex);
        }
        return super.resolveToSingleError(ex, env);
    }

    private GraphQLError toGraphqlException(Throwable throwable) {
        return GraphqlErrorBuilder.newError()
                .message(throwable.getMessage())
                .errorType(ErrorType.DataFetchingException)
                .build();
    }

}