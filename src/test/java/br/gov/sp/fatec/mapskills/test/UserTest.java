package br.gov.sp.fatec.mapskills.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import br.gov.sp.fatec.mapskills.config.SpringContextConfiguration;
import br.gov.sp.fatec.mapskills.domain.institution.Institution;
import br.gov.sp.fatec.mapskills.domain.institution.InstitutionService;
import br.gov.sp.fatec.mapskills.domain.institution.Mentor;
import br.gov.sp.fatec.mapskills.domain.user.Administrator;
import br.gov.sp.fatec.mapskills.domain.user.MapSkillsException;
import br.gov.sp.fatec.mapskills.domain.user.ProfileType;
import br.gov.sp.fatec.mapskills.domain.user.Student;
import br.gov.sp.fatec.mapskills.domain.user.User;
import br.gov.sp.fatec.mapskills.domain.user.UserFactory;
import br.gov.sp.fatec.mapskills.domain.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringContextConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class UserTest implements ApplicationTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InstitutionService institutionService;
	
	@Resource
	private Map<ProfileType, UserFactory> userFactory;
	
	@Test
	public void saveStudent() throws MapSkillsException {
		final Student student = new Student("1460281423023", "Student Mock", "1289003400", "nick5s2@fate.sp.gov.br", "mudar@123");
		userService.save(student);
		
		assertEquals("Student Mock", userService.findById(student.id()).name());
	}
	
	@Test
	public void saveStudentList() throws MapSkillsException {
		final List<Student> students = new ArrayList<>();
		final Student studentA = new Student("1460281423030", "Student MockA", "1289003400", "studentA@fate.sp.gov.br", "mudar@123");
		final Student studentB = new Student("1460281423031", "Student MockB", "1289003400", "studentB@fate.sp.gov.br", "mudar@123");
		final Student studentC = new Student("1460281423032", "Student MockC", "1289003400", "studentC@fate.sp.gov.br", "mudar@123");
		final Student studentD = new Student("1460281423033", "Student MockD", "1289003400", "studentD@fate.sp.gov.br", "mudar@123");
		students.add(studentA);
		students.add(studentB);
		students.add(studentC);
		students.add(studentD);
		
		userService.save(students);
	
	}
	
	@Test
	public void findAllStudentsByInstitution() {
		
		assertEquals(4, institutionService.findAllStudentsByInstitution(146).size());
	}
	
	@Test
	public void findUserByUsernamePassword() throws MapSkillsException {
		final String EXPECTED = "1460281423023"; 
		
		final Student studentSave = new Student("1460281423023", "Student Mock", "1289003400", "studentA@fatec.sp.gov.br", "mudar@123");
		
		final Mentor mentorSave = new Mentor("Mentor Fatec GRU","marquinho@fatec.sp.gov.br","mudar@123");
		final Institution institutionSave = new Institution(200, "22238846000105","FATEC GRU","Guarulhos", mentorSave);
		
		userService.save(studentSave);
		institutionService.saveInstitution(institutionSave);
		
		final User studentUser = userService.findUserByUsernamePassword("studentA@fatec.sp.gov.br", "mudar@123");
		final User mentorUser = userService.findUserByUsernamePassword("marquinho@fatec.sp.gov.br", "mudar@123");
		
		final Student student = userFactory.get(studentUser.profile()).create(studentUser);
		final Mentor mentor = userFactory.get(mentorUser.profile()).create(mentorUser);
		
		assertEquals(EXPECTED, student.ra());
		assertEquals("Mentor Fatec GRU", mentor.name());
	}
	
	@Test
	public void saveAdministrator() {
		final Administrator admin = new Administrator("Administrador", "admin", "admin");
		userService.save(admin);
	}

}