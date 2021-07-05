package com.studio.asylum.api.query;

public interface QueryHandler<R, Q extends Query<R>> {

    R handle(Q query);

}
