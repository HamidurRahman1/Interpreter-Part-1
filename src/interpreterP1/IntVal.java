
package interpreterP1;

class IntVal extends Val
{
    int val;

    public IntVal(int i)
    {
        val = i;
    }

    public Val cloneVal()
    {
        return new IntVal(val);
    }

    public float floatVal()
    {
        return (float)val;
    }

    public boolean isZero()
    {
        return val == 0;
    }

    @Override
    public String toString() {
        return val+"";
    }
}