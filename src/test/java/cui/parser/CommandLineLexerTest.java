package cui.parser;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class CommandLineLexerTest {
	
	@Test
	public void testLexicalize1() {
		CommandLineLexer lexer = new CommandLineLexer();
		assertEquals("ls", lexer.lexicalize("ls").get(0).toString());
	}
	
	@Test
	public void testLexicalize2() {
		CommandLineLexer lexer = new CommandLineLexer();
		String command = "echo a ; grep a";
		assertEquals("echo a ; grep a", lexer.lexicalize(command).get(0).toString());
	}
	
	@Test
	public void testLexicalize3() {
		CommandLineLexer lexer = new CommandLineLexer();
		String command = "echo a && grep a";
		ArrayList<Token> tokens = lexer.lexicalize(command);
		assertEquals("echo a", tokens.get(0).toString());
		assertEquals("&&", tokens.get(1).toString());
		assertEquals("grep a", tokens.get(2).toString());
	}
	
	@Test
	public void testLexicalize4() {
		CommandLineLexer lexer = new CommandLineLexer();
		String command = "echo a & grep a";
		assertEquals("echo a & grep a", lexer.lexicalize(command).get(0).toString());
	}
	
	@Test
	public void testLexicalize5() {
		CommandLineLexer lexer = new CommandLineLexer();
		String command = "echo a || grep a";
		ArrayList<Token> tokens = lexer.lexicalize(command);
		assertEquals("echo a", tokens.get(0).toString());
		assertEquals("||", tokens.get(1).toString());
		assertEquals("grep a", tokens.get(2).toString());
	}
	
	@Test
	public void testLexicalize6() {
		CommandLineLexer lexer = new CommandLineLexer();
		String command = "echo a | grep a";
		ArrayList<Token> tokens = lexer.lexicalize(command);
		assertEquals("echo a", tokens.get(0).toString());
		assertEquals("|", tokens.get(1).toString());
		assertEquals("grep a", tokens.get(2).toString());
	}
	
	@Test
	public void testLexicalize7() {
		CommandLineLexer lexer = new CommandLineLexer();
		String command = "cd \\test";
		ArrayList<Token> tokens = lexer.lexicalize(command);
		assertEquals("cd \\test", tokens.get(0).toString());
	}
	
	@Test
	public void testLexicalize8() {
		CommandLineLexer lexer = new CommandLineLexer();
		String command = "cd \\test\\test";
		ArrayList<Token> tokens = lexer.lexicalize(command);
		assertEquals("cd \\test\\test", tokens.get(0).toString());
	}
}
