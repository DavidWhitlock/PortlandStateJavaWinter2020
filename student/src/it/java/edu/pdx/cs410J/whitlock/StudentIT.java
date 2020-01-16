package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;

/**
 * Integration tests for the <code>Student</code> class's main method.
 * These tests extend <code>InvokeMainTestCase</code> which allows them
 * to easily invoke the <code>main</code> method of <code>Student</code>.
 */
public class StudentIT extends InvokeMainTestCase {
  @Test
  public void invokingMainWithNoArgumentsHasExitCodeOf1() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void invokingMainWithNoArgumentsPrintsMissingArgumentsToStandardError() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class);
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));
  }

  @Test
  public void invokingMainWithoutGenderPrintsErrorMessageAndExits1() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class, "Student");
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing gender"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void invokingMainWithoutGPAPrintsErrorMessageAndExits1() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class, "Student", "other");
    assertThat(result.getTextWrittenToStandardError(), containsString("Missing gpa"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void invokingMainWithZeroClassesPrintsOutStudentAndExits0() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class, "Student", "other", "1.23");
    assertThat(result.getTextWrittenToStandardOut(), containsString("Student has a GPA of 1.23 and is taking 0 classes."));
    assertThat(result.getExitCode(), equalTo(0));
  }

  @Test
  public void unknownGenderPrintsErrorMessageAndExits1() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class, "other", "Student", "1.23");
    assertThat(result.getTextWrittenToStandardError(), containsString("Unknown gender: Student"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Test
  public void invalidGpaPrintsErrorMessageAndExits1() {
    InvokeMainTestCase.MainMethodResult result = invokeMain(Student.class, "other", "Student", "GPA!!");
    assertThat(result.getTextWrittenToStandardError(), containsString("Invalid GPA: GPA!!"));
    assertThat(result.getExitCode(), equalTo(1));
  }

  @Ignore
  @Test
  public void runMainWithDaveStudent() {
    MainMethodResult main = invokeMain(Student.class, "Dave", "male", "3.64", "Algorithms", "Operating Systems", "Java");
    assertThat(main.getExitCode(), equalTo(0));
    assertThat(main.getTextWrittenToStandardOut(),
      containsString("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating " +
                     "Systems, and Java. He says \"This class is too much work\"."));
  }

}
