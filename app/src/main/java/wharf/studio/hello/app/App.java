package wharf.studio.hello.app;

import java.time.LocalDate;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.tutorial.persistent.Artist;
import org.apache.cayenne.tutorial.persistent.Gallery;
import org.apache.cayenne.tutorial.persistent.Painting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Autowired
	private ServerRuntime cayenneRuntime;

	@Override
	public void run(String... args) throws Exception {
		ObjectContext context = cayenneRuntime.newContext();

		Artist picasso = context.newObject(Artist.class);
		picasso.setName("Pablo Picasso");
		picasso.setDateOfBirth(LocalDate.of(1881, 10, 25));

		Gallery metropolitan = context.newObject(Gallery.class);
		metropolitan.setName("Metropolitan Museum of Art");

		Painting girl = context.newObject(Painting.class);
		girl.setName("Girl Reading at a Table");

		Painting stein = context.newObject(Painting.class);
		stein.setName("Gertrude Stein");

		picasso.addToPaintings(girl);
		picasso.addToPaintings(stein);

		girl.setGallery(metropolitan);
		stein.setGallery(metropolitan);

		context.commitChanges();

		// SelectQuery examples
		ObjectSelect.query(Painting.class).select(context);

		ObjectSelect.query(Painting.class)
				.where(Painting.NAME.likeIgnoreCase("gi%")).select(context);

		ObjectSelect.query(Painting.class)
				.where(Painting.ARTIST.dot(Artist.DATE_OF_BIRTH)
						.lt(LocalDate.of(1900,1,1))).select(context);
	}
}
