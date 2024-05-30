import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import poe_part2.Login;
import poe_part2.Registration;
import poe_part2.Task;

public class LoginRegistrationTest {

    private String regUsername1 = "kyl_1";
    private String regUsername2 = "kyle!!!!!!!";
    private String regPassword1 = "Ch&&sec@ke99!";
    private String regPassword2 = "password";

    private String loginUsername = "kyl_1";
    private String loginPassword = "Ch&&sec@ke99!";

    @Test
    public void testRegistrationUsernameFormatted() {
        Registration registration = new Registration(regUsername1, regPassword1);
        assertTrue(registration.checkUserName());
    }

    @Test
    public void testRegistrationUsernameNotFormatted() {
        Registration registration = new Registration(regUsername2, regPassword1);
        assertFalse(registration.checkUserName());
    }

    @Test
    public void testRegistrationPasswordComplexity() {
        Registration registration = new Registration(regUsername1, regPassword1);
        assertTrue(registration.checkPasswordComplexity());
    }

    @Test
    public void testRegistrationPasswordNotComplex() {
        Registration registration = new Registration(regUsername1, regPassword2);
        assertFalse(registration.checkPasswordComplexity());
    }

    @Test
    public void testLoginSuccessful() {
        Login login = new Login(loginUsername, loginPassword);
        assertTrue(login.verifyLogin(loginUsername, loginPassword));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login(loginUsername, loginPassword);
        assertFalse(login.verifyLogin(loginUsername, "wrongPassword"));
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        Registration registration = new Registration(regUsername1, regPassword1);
        assertTrue(registration.checkUserName());
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Registration registration = new Registration(regUsername2, regPassword1);
        assertFalse(registration.checkUserName());
    }

    @Test
    public void testPasswordMeetsComplexityRequirements() {
        Registration registration = new Registration(regUsername1, regPassword1);
        assertTrue(registration.checkPasswordComplexity());
    }

    @Test
    public void testPasswordDoesNotMeetComplexityRequirements() {
        Registration registration = new Registration(regUsername1, regPassword2);
        assertFalse(registration.checkPasswordComplexity());
    }

    @Test
    public void testTaskDescriptionWithinLimit() {
        Task task = new Task("Task", 0, "This is a valid description", "Dev Name", 5, "To Do");
        assertTrue(task.checkTaskDescription());
    }

    @Test
    public void testTaskDescriptionExceedsLimit() {
        Task task = new Task("Task", 0, "This description is way too long and should fail the check", "Dev Name", 5, "To Do");
        assertFalse(task.checkTaskDescription());
    }

    @Test
    public void testTaskIDCreation() {
        Task task = new Task("Add Task Feature", 1, "Create Add Task feature to add task users", "Mike Smith", 10, "Doing");
        assertEquals("AD:1:ITH", task.createTaskID());
    }

    @Test
    public void testTotalHoursCalculation() {
        Task task1 = new Task("Task1", 0, "Description1", "Dev1", 5, "To Do");
        Task task2 = new Task("Task2", 1, "Description2", "Dev2", 10, "Doing");
        assertEquals(15, task1.getTaskDuration() + task2.getTaskDuration());
    }
}
