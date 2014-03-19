import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import models.Usuario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class UsuarioTest {
	
	Usuario usuario;
	
	@Before
	public void setUp() {
		usuario = new Usuario("franklin", "franklin@gmail.com", "123");
		start(fakeApplication(inMemoryDatabase()));
		usuario.save();
	}
	
	@Test
	public void test() {
		Assert.assertEquals("franklin", usuario.getNome());
		Assert.assertEquals("franklin@gmail.com", usuario.getLogin());
		Assert.assertEquals("123", usuario.getSenha());
		Usuario user = Usuario.find.all().get(0);
		Assert.assertEquals(usuario, user);
		usuario.setLogin("f@gmail.com");
		Assert.assertEquals("f@gmail.com", usuario.getLogin());
		usuario.setNome("f");
		Assert.assertEquals("f", usuario.getNome());
		usuario.setSenha("1");
		Assert.assertEquals("1", usuario.getSenha());
	}

}
