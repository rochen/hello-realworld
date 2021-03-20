package wharf.studio.realworld.query;

public interface QueryHandler<R, Q extends Query<R>> {

    R handle(Q query);

}
