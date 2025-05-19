package boundary;


import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PositiveNumbersFilter extends DocumentFilter {

	@Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        if (isPositiveNumber(string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

	@Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (isPositiveNumber(text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

	@Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isPositiveNumber(String text) {
        // Check if the text contains only digits and does not start with a negative sign
        return text.matches("\\d*");
    }
}
