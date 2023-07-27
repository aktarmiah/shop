package com.generic.retailer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public final class Cli implements AutoCloseable {


	private HashMap<String, Product> productList;
	private Trolley trolley;

	public static Cli create(String prompt, BufferedReader reader, BufferedWriter writer, LocalDate date) {
		requireNonNull(prompt);
		requireNonNull(reader);
		requireNonNull(writer);
		return new Cli(prompt, reader, writer, date);
	}

	public static Cli create(BufferedReader reader, BufferedWriter writer) {
		return new Cli(">", reader, writer, LocalDate.now());
	}

	private static final Predicate<String> WHITESPACE = Pattern.compile("^\\s{0,}$").asPredicate();

	private final String prompt;
	private final BufferedReader reader;
	private final BufferedWriter writer;
	private final LocalDate date;


	private Cli(String prompt, BufferedReader reader, BufferedWriter writer, LocalDate date) {
		this.prompt = prompt;
		this.reader = reader;
		this.writer = writer;
		this.date = date;
		this.trolley = new Trolley();
		this.productList = new HashMap<>();
		this.productList.put("cd", new CD());
		this.productList.put("dvd", new DVD());
		this.productList.put("book", new Book());

	}

	private void prompt() throws IOException {
		writeLine(prompt);
	}

	private Optional<String> readLine() throws IOException {
		String line = reader.readLine();
		return line == null || WHITESPACE.test(line) ? Optional.empty() : Optional.of(line);
	}

	private void writeLine(String line) throws IOException {
		writer.write(line + " ");
		writer.flush();
	}

	public void run() throws IOException {
		writeLine("What would you like to buy?");
		prompt();
		Optional<String> line = readLine();
		while (line.isPresent()) {
			String input = line.get().toLowerCase().trim();

			if(input.equals("no")) {
				break;
			}

			//Check if the requested product exists in the product list
			Product requestedProduct = this.productList.getOrDefault(input, null);

			if( requestedProduct == null) {
				writeLine(String.format("It looks like we don't have %s in stock.", input));
			} else {
				trolley.add(requestedProduct);
			}

			writeLine("Anything else?");
			prompt();
			line = readLine();
		}

		System.out.println();
		writeLine( "Thank you for visiting Generic Retailer" );
		System.out.println();
		System.out.println();
		System.out.println(this.trolley.getReceipt());
	}

	@Override
	public void close() throws Exception {
		reader.close();
		writer.close();
	}

}
