package main

import "C"

import "github.com/JosephNaberhaus/agnostic/language/lexer"

//export ParseAgnosticAST
func ParseAgnosticAST(rawText string) string {
	ast, _ := lexer.Test(rawText)
	return ast.Name
}

func main() {}