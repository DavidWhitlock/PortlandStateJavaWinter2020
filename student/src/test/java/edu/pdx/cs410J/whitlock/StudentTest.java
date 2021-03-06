package edu.pdx.cs410J.whitlock;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Unit tests for the Student class.  In addition to the JUnit annotations,
 * they also make use of the <a href="http://hamcrest.org/JavaHamcrest/">hamcrest</a>
 * matchers for more readable assertion statements.
 */
public class StudentTest
{

  @Test
  public void studentNamedPatIsNamedPat() {
    String name = "Pat";
    var pat = new Student(name, new HashSet<>(), 0.0, "other");
    assertThat(pat.getName(), equalTo(name));
  }

  @Test
  public void studentHasExpectedGpa() {
    double gpa = 3.25;
    var student = createStudentWithGpa(gpa);
    assertThat(student.getGpa(), equalTo(gpa));
  }

  private Student getDave() {
    HashSet<String> classes = new LinkedHashSet<>();
    classes.add("Algorithms");
    classes.add("Operating Systems");
    classes.add("Java");
    return new Student("Dave", classes, 3.64, "male");
  }

  @Test
  public void exampleStudentFromAssignment() {
    Student dave = getDave();

    assertThat(dave.toString(), equalTo("Dave has a GPA of 3.64 and is taking 3 classes: Algorithms, Operating " +
      "Systems, and Java.  He says \"This class is too much work\"."));
  }

  @Test
  public void toStringContainsStudentName() {
    assertThat(getDave().toString(), containsString("Dave"));
  }

  @Test
  public void toStringContainsGpa() {
    assertThat(getDave().toString(), containsString(" has a GPA of 3.64 "));
  }

  @Test
  public void toStringContainsAnotherGPA() {
    double gpa = 2.5;
    Student student = createStudentWithGpa(gpa);
    assertThat(student.toString(), containsString(" has a GPA of 2.5 "));
  }

  private Student createStudentWithGpa(double gpa) {
    return new Student("Name", new HashSet<>(), gpa, "Other");
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenGpaIs25Point5AnIllegalArgumentExceptionIsThrown() {
    createStudentWithGpa(25.5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void whenGpaLessThanZeroAnIllegalArgumentExceptionIsThrown() {
    createStudentWithGpa(-25.5);
  }

  @Test
  public void davesToStringContains3Classes() {
    String classes = "and is taking 3 classes";
    assertThat(getDave().toString(), containsString(classes));
  }

  @Test
  public void whenStudentTakes1ClassTheWordClassIsSingular() {
    HashSet<String> classes = new HashSet<>();
    classes.add("One Class");
    Student student = new Student("Name", classes, 1.23, "Other");
    assertThat(student.toString(), containsString("and is taking 1 class:"));
  }

  @Test
  public void whenStudentTakes0ClassesNoClassesAreListed() {
    HashSet<String> classes = new HashSet<>();
    Student student = new Student("Name", classes, 1.23, "Other");
    assertThat(student.toString(), containsString("and is taking 0 classes."));
  }

  @Test
  public void whenStudentTakes1ClassTheListShouldNotHaveAComma() {
    HashSet<String> classes = new HashSet<>();
    classes.add("One Class");
    Student student = new Student("Name", classes, 1.23, "Other");
    assertThat(student.toString(), containsString("1 class: One Class."));
    assertThat(student.toString(), not(containsString(",")));
  }

  @Test
  public void whenStudentTakes2ClassTheListShouldNotHaveACommaButItHasAnAnd() {
    HashSet<String> classes = new LinkedHashSet<>();
    classes.add("One Class");
    classes.add("Another Class");
    Student student = new Student("Name", classes, 1.23, "Other");
    assertThat(student.toString(), containsString("2 classes: One Class and Another Class."));
    assertThat(student.toString(), not(containsString(",")));
  }

  @Test
  public void whenStudentTakes3ClassTheListHasCommaAndAnd() {
    HashSet<String> classes = new LinkedHashSet<>();
    classes.add("One Class");
    classes.add("Another Class");
    classes.add("Yet Another Class");
    Student student = new Student("Name", classes, 1.23, "Other");
    assertThat(student.toString(), containsString("3 classes: One Class, Another Class, and Yet Another Class."));
  }

  @Test
  public void davesToStringContainsHisClasses() {
    String classes = "and is taking 3 classes: Algorithms, Operating Systems, and Java.";
    assertThat(getDave().toString(), containsString(classes));
  }

  @Test
  public void femaleStudentHasShePronoun() {
    Student student = new Student("Student", new HashSet<>(), 1.2, "female");
    assertThat(student.getGenderPronoun(), equalTo("She"));
  }

  @Test
  public void maleStudentHasHePronoun() {
    Student student = new Student("Student", new HashSet<>(), 1.2, "male");
    assertThat(student.getGenderPronoun(), equalTo("He"));
  }

  @Test
  public void otherGenderedStudentHasTheyPronoun() {
    Student student = new Student("Student", new HashSet<>(), 1.2, "other");
    assertThat(student.getGenderPronoun(), equalTo("They"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void studentWithUnknownGenderThrowsIllegalArgumentException() {
    new Student("Student", new HashSet<>(), 1.2, "unknown");
  }

  @Test
  public void otherGenderIsCaseInsensitive() {
    Student student = new Student("Student", new HashSet<>(), 1.2, "Other");
    assertThat(student.getGenderPronoun(), equalTo("They"));
  }

  @Test
  public void femaleGenderIsCaseInsensitive() {
    Student student = new Student("Student", new HashSet<>(), 1.2, "Female");
    assertThat(student.getGenderPronoun(), equalTo("She"));
  }

  @Test
  public void maleGenderIsCaseInsensitive() {
    Student student = new Student("Student", new HashSet<>(), 1.2, "Male");
    assertThat(student.getGenderPronoun(), equalTo("He"));
  }

  @Test
  public void allStudentsSayThisClassIsTooMuchWork() {
    Student student = new Student("Student", new HashSet<>(), 1.2, "Male");
    assertThat(student.says(), equalTo("This class is too much work"));
  }
}
