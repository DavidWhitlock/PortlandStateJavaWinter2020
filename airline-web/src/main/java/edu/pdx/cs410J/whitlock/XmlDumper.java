package edu.pdx.cs410J.whitlock;

import edu.pdx.cs410J.AirlineDumper;

import java.io.IOException;
import java.io.PrintWriter;

public class XmlDumper implements AirlineDumper<Airline> {
  private final PrintWriter pw;

  public XmlDumper(PrintWriter pw) {
    this.pw = pw;
  }

  @Override
  public void dump(Airline airline) throws IOException {

    String xml = airline.getName() + " " + airline.getFlights().iterator().next().getNumber();

    this.pw.println(xml);
  }
}
