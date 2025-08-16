# Cool Language Compiler - Code Generation

## What This Does

Transforms semantically validated Cool programs into executable MIPS assembly code, handling object-oriented features, memory management, and runtime support for Cool language constructs.

## How It Works

### Code Generation Strategy
- **AST Traversal**: Post-order traversal of type-annotated AST to generate assembly bottom-up
- **Object Layout**: Implements Cool's object model with virtual method tables and attribute storage
- **Runtime Integration**: Links with Cool runtime system for garbage collection and built-in methods
- **Register Management**: Efficient allocation and spilling strategies for MIPS register constraints

### Object-Oriented Translation
- **Virtual Dispatch**: Implements dynamic method dispatch through class vtables
- **Inheritance Support**: Handles method inheritance and overriding at runtime
- **Object Creation**: Generates proper object initialization and constructor calls
- **Type Information**: Embeds runtime type information for dynamic type checking

### Memory Management Implementation
- **Heap Organization**: Structured heap layout compatible with Cool's garbage collector
- **Stack Frame Management**: Standardized activation records for method calls
- **Register Usage**: Follows MIPS calling conventions with Cool-specific optimizations
- **Constant Pool**: Efficient storage and access for string literals and class metadata

## Key Technical Decisions

### Why MIPS Assembly Target
- **Educational Value**: MIPS provides clear, regular instruction set for learning code generation
- **Runtime Compatibility**: Existing Cool runtime system supports MIPS execution
- **Debugging Support**: SPIM simulator enables easy testing and debugging

### Code Generation Approach
- **Template-Based**: Uses code templates for common Cool constructs (method calls, object creation)
- **Peephole Optimization**: Basic local optimizations for instruction sequences
- **Runtime Cooperation**: Generates code that cooperates with garbage collector and runtime checks

## Input/Output

**Input**: Type-annotated AST and symbol tables from semantic analysis
**Output**: 
- Executable MIPS assembly (`.s` files)
- Runtime-compatible object code
- Debug information and symbol mappings

## Compiler Pipeline Integration

This is the **final phase** of the complete Cool compiler:

**Previous Phases**: 
- [Lexical & Syntactic Analysis](https://github.com/mihneablotiu/Compilers-Lexical-And-Syntactic-Analysis) - Provides program structure
- [Semantic Analysis](https://github.com/mihneablotiu/Compilers-Semantic-Analysis) - Ensures type safety and symbol resolution

**Output**: The generated MIPS assembly can be executed on SPIM simulator or MIPS hardware, completing the transformation from Cool source code to executable program.
