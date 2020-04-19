
package interpreterP1;

public class NilVal extends Val
{
    @Override
    public Val cloneVal() {
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf("nil");
    }
}