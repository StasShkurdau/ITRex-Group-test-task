package sample;

public class ClassToFirstTask {

    public String  simplifiesEnglishText(String string){
        string = removeC(string);
        string = removeDoubleLetter(string);
        string = removeArticles(string);
        string = removeE(string);
        return string;
    }

    public String removeC(String inputString){
        StringBuffer returnBuffer = new StringBuffer(inputString + " ");
        StringBuffer subBuffer = new StringBuffer();
        for (int i = 0; i < inputString.length(); i++){
            subBuffer.replace(0,2, returnBuffer.substring(i, i+2));
            switch (subBuffer.toString()) {
                case("ci"):
                case("ce"):
                    returnBuffer.setCharAt(i, 's');
                    break;
                case("ck"):
                    returnBuffer.deleteCharAt(i);
                    returnBuffer.append(" ");
                    continue;
            }
            if(subBuffer.charAt(0) == 'c'){
                returnBuffer.setCharAt(i, 'k');
            }

        }
        return deleteWhitespacesAtTheEnd(returnBuffer).toString();

    }

    public String removeDoubleLetter(String inputString){
        StringBuffer returnBuffer = new StringBuffer(inputString + " ");
        StringBuffer subBuffer = new StringBuffer();
        for (int i = 0; i < inputString.length(); i++){
            subBuffer.replace(0,2, returnBuffer.substring(i, i+2));
            switch (subBuffer.toString()) {
                case("ee"):
                    returnBuffer.replace(i, i+2, "i");
                    returnBuffer.append(" ");
                    break;
                case("oo"):
                    returnBuffer.replace(i, i+2, "y");
                    returnBuffer.append(" ");
                    break;
            }
            if(subBuffer.charAt(0) == subBuffer.charAt(1)){
                returnBuffer.deleteCharAt(i);
                returnBuffer.append(" ");
            }

        }
        return deleteWhitespacesAtTheEnd(returnBuffer).toString();

    }

    public String removeE(String inputString){
        StringBuffer returnBuffer = new StringBuffer(inputString + " ");
        StringBuffer subBuffer = new StringBuffer();
        for (int i = 0; i < inputString.length(); i++) {
            subBuffer.replace(0,2, returnBuffer.substring(i, i+2));
            switch (subBuffer.toString()) {
                case("e "):
                case("e,"):
                case("e."):
                case("e\n"):
                    returnBuffer.deleteCharAt(i);
                    returnBuffer.append(" ");
                    break;
            }
        }
        return deleteWhitespacesAtTheEnd(returnBuffer).toString();

    }

    public String removeArticles(String inputString){
        StringBuffer returnBuffer = new StringBuffer(inputString + " ");
        for (int i = 0; i < inputString.length() - 4; i++) {
            if(returnBuffer.substring(i, i + 3).equals(" a ")){
                returnBuffer.delete(i, i+2);
                returnBuffer.append("   ");
            }
            if(returnBuffer.substring(i, i + 4).equals(" an ")){
                returnBuffer.delete(i, i+3);
                returnBuffer.append("    ");
            }
            if(returnBuffer.substring(i, i + 5).equals(" the ")){
                returnBuffer.delete(i, i+4);
                returnBuffer.append("     ");
            }
        }
        return deleteWhitespacesAtTheEnd(returnBuffer).toString();

    }

    private StringBuffer deleteWhitespacesAtTheEnd(StringBuffer buffer){
        System.out.println(buffer.length());
        for(int i = buffer.length()-1; buffer.charAt(i) == ' '; i--){
            buffer.deleteCharAt(i);
        }
        return buffer;
    }


}
