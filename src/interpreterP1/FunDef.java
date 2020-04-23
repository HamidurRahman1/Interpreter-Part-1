
package interpreterP1;

public class FunDef extends FunDefList
{
    public Header header;
    public Exp exp;

    public FunDef(Header h, Exp e)
    {
        header = h;
        exp = e;
    }

    public void printParseTree(String indent)
    {
        String indent1 = indent+" ";

        IO.displayln(indent + indent.length() + " <fun def>");
        header.printParseTree(indent1);
        exp.printParseTree(indent1);
        IO.displayln("");
    }
}