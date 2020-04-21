
package interpreterP1;

class BoolVal extends Val
{
    boolean val;

    public BoolVal(boolean boolVal)
    {
        val = boolVal;
    }

    @Override
    public Val cloneVal() {
        return null;
    }

    @Override
    public String toString() {
        return val+"";
    }
}