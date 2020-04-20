
package interpreterP1;

class BoolVal extends Val
{
    boolean val;

    public BoolVal(boolean val)
    {
        this.val = val;
    }

    @Override
    public Val cloneVal() {
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}