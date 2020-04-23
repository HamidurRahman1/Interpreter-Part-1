
package interpreterPart1;

public class IntVal extends Val
{
    public int val;

    public IntVal(int i)
    {
        val = i;
    }

    public Val cloneVal()
    {
        return new IntVal(val);
    }

    @Override
    public String toString()
    {
        return String.valueOf(val);
    }
}