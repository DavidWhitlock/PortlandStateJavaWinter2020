package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.lang.Human;

import java.util.Iterator;
import java.util.Set;

/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {

  private final double gpa;
  private final Set<String> classes;
  private String genderPronoun;

  /**
   * Creates a new <code>Student</code>                                             
   *                                                                                
   * @param name                                                                    
   *        The student's name                                                      
   * @param classes                                                                 
   *        The names of the classes the student is taking.  A student              
   *        may take zero or more classes.                                          
   * @param gpa                                                                     
   *        The student's grade point average                                       
   * @param gender                                                                  
   *        The student's gender ("male" or "female", or "other", case insensitive)
   */                                                                               
  public Student(String name, Set<String> classes, double gpa, String gender) {
    super(name);

    if (gpa > 4.0) {
      throw new IllegalArgumentException(gpa + " is too big");

    } else if (gpa < 0.0) {
      throw new IllegalArgumentException(gpa + " is too small");
    }

    this.classes = classes;


    this.gpa = gpa;

    if (gender.equals("female")) {
      this.genderPronoun = "She";

    } else if (gender.equals("male")) {
      this.genderPronoun = "He";

    } else if (gender.equals("other")) {
      this.genderPronoun = "They";

    } else {
      throw new IllegalArgumentException("Unknown gender: " + gender);
    }
  }

  /**
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    throw new UnsupportedOperationException("Not implemented yet");
  }

  double getGpa() {
    return this.gpa;
  }

  /**
   * Returns a <code>String</code> that describes this
   * <code>Student</code>.
   */
  @Override
  public String toString() {
    int numberOfClasses = this.classes.size();
    StringBuilder sb = new StringBuilder()
      .append(getName()).append(" has a GPA of ").append(getGpa())
      .append(" and is taking ").append(numberOfClasses).append(" class");
    appendNumberOfClassesSuffix(numberOfClasses, sb);

    appendClassNames(sb);

    return sb.toString();
  }

  private void appendClassNames(StringBuilder sb) {
    int numberOfClasses = this.classes.size();
    if (numberOfClasses == 1) {
      sb.append(this.classes.iterator().next());

    } else if (numberOfClasses == 2) {
      Iterator<String> iter = this.classes.iterator();
      sb.append(iter.next());
      sb.append(" and ");
      sb.append(iter.next());

    } else {
      for (Iterator<String> iter = this.classes.iterator(); iter.hasNext(); ) {
        String className = iter.next();
        if (!iter.hasNext()) {
          sb.append("and ");
        }
        sb.append(className);
        if (iter.hasNext()) {
          sb.append(", ");
        }
      }
    }


    sb.append(".");
  }

  private void appendNumberOfClassesSuffix(int numberOfClasses, StringBuilder sb) {
    switch (numberOfClasses) {
      case 0:
        sb.append("es.");
        break;
      case 1:
        sb.append(": ");
        break;
      default:
        sb.append("es: ");
        break;
    }
  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    System.err.println("Missing command line arguments");
    System.exit(1);
  }

  public String getGenderPronoun() {
    return genderPronoun;
  }
}