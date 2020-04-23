
package interpreterPart1;

public class NilVal extends Val
{
    @Override
    public Val cloneVal() {
        return new NilVal();
    }

    @Override
    public String toString() {
        return "nil";
    }
}