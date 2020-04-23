
package interpreterPart1;

public abstract class Parser extends LexAnalyzer
{
    public static final EmptyParameterList emptyParameterList = new EmptyParameterList();
    public static final Nil nil = new Nil();
    public static final EmptyExpList emptyExpList = new EmptyExpList();
    public static boolean syntaxErrorFound = false;


    public static FunDefList funDefList()
    {
        FunDef funDef = funDef();
        if ( state == State.Id )
        {
            FunDefList funDefList = funDefList();
            return new MultipleFunDef(funDef, funDefList);
        }
        else
            return funDef;
    }

    public static FunDef funDef()
    {
        if ( state == State.Id )
        {
            Header header = header();
            if (state == State.LBrace )
            {
                getToken();
                Exp exp = exp();
                if ( state == State.RBrace )
                {
                    getToken();
                    return new FunDef(header, exp);
                }
                else
                {
                    errorMsg(2);
                    return null;
                }
            }
            else
            {
                errorMsg(1);
                return null;
            }
        }
        else
        {
            errorMsg(0);
            return null;
        }
    }

    public static Header header()
    {
        String funName = t;
        getToken();
        ParameterList parameterList = parameterList();
        return new Header(funName, parameterList);
    }

    public static ParameterList parameterList()
    {
        if ( state == State.Id )
        {
            String id = t;
            getToken();
            ParameterList parameterList = parameterList();
            return new NonEmptyParameterList(id, parameterList);
        }
        else
            return emptyParameterList;
    }

    public static Exp exp()
    {
        switch ( state )
        {
            case Id:
                Id id = new Id(t);
                getToken();
                return id;

            case Int:
                Int intElem = new Int(Integer.parseInt(t));
                getToken();
                return intElem;

            case Float: case FloatE: case FloatF:
            Floatp floatElem = new Floatp(Float.parseFloat(t));
            getToken();
            return floatElem;

            case Keyword_nil:
                getToken();
                return nil;

            case LParen:
                getToken();
                FunExp funExp = funExp();
                if ( state == State.RParen )
                {
                    getToken();
                    return funExp;
                }
                else
                {
                    errorMsg(3);
                    return null;
                }

            case Keyword_if:
                getToken();
                Exp exp1 = exp();
                if ( state == State.Keyword_then )
                {
                    getToken();
                    Exp exp2 = exp();
                    if ( state == State.Keyword_else )
                    {
                        getToken();
                        Exp exp3 = exp();
                        return new If(exp1, exp2, exp3);
                    }
                    else
                        errorMsg(5);
                }
                else
                    errorMsg(4);
                return null;

            default:
                errorMsg(6);
                return null;
        }
    }

    public static FunExp funExp()
    {
        if ( state == State.Id )
        {
            Id id = new Id(t);
            getToken();
            ExpList expList = expList();
            return new FunCall(id, expList);
        }
        else if ( state.isPairOp() || state.isArithOp() || state.isBoolOp() || state.isCompOp() )
        {
            State opState = state;
            getToken();
            ExpList expList = expList();
            switch ( opState )
            {
                case Keyword_pair:   return new Pair(expList);
                case Keyword_first:  return new First(expList);
                case Keyword_second: return new Second(expList);
                case Add:            return new AddE(expList);
                case Sub:            return new SubE(expList);
                case Mul:            return new MulE(expList);
                case Div:            return new DivE(expList);
                case Keyword_or:     return new OrE(expList);
                case Keyword_and:    return new AndE(expList);
                case Keyword_not:    return new NotE(expList);
                case Lt:             return new LtE(expList);
                case Le:             return new LeE(expList);
                case Gt:             return new GtE(expList);
                case Ge:             return new GeE(expList);
                default:             return new EqE(expList);
            }
        }
        else
        {
            errorMsg(7);
            return null;
        }
    }

    public static boolean beginsExp()
    {
        return state.compareTo(State.Id) >= 0 && state.compareTo(State.FloatF) <= 0 ||
                        state == State.LParen || state == State.Keyword_if || state == State.Keyword_nil;
    }

    public static ExpList expList()
    {
        if ( beginsExp() )
        {
            Exp exp = exp();
            ExpList expList = expList();
            return new NonEmptyExpList(exp, expList);
        }
        else
            return emptyExpList;
    }

    public static void errorMsg(int i)
    {
        syntaxErrorFound = true;
        String msg = "";

        display(t + " : Syntax Error, unexpected symbol where ");

        switch( i )
        {
            case 0:	msg = "fun name expected"; break;
            case 1:	msg = "{ expected"; break;
            case 2: msg = "} expected"; break;
            case 3: msg = ") expected"; break;
            case 4:	msg = "then expected"; break;
            case 5:	msg = "else expected"; break;
            case 6:	msg = "id, int, float, nil, (, or if expected"; break;
            case 7:	msg = "fun name, pair, first, second, arith op, bool op, or comp op expected"; break;
        }

        displayln(msg);
        return;
    }
}
