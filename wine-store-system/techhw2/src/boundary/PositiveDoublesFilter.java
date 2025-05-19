package boundary;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PositiveDoublesFilter extends DocumentFilter {

	@Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;
        if (isValidPositiveDouble(newText)) {
            super.insertString(fb, offset, string, attr);
        }
    }

	@Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = currentText.substring(0, offset) + text + currentText.substring(offset + length);
        if (isValidPositiveDouble(newText)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

	@Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        String newText = fb.getDocument().getText(0, fb.getDocument().getLength() - length);
        if (isValidPositiveDouble(newText)) {
            super.remove(fb, offset, length);
        }
    }

    private boolean isValidPositiveDouble(String text) {
        if (text.isEmpty() || text.equals(".")) {
            return true; // Allow empty string or just a decimal point
        }
        if (text.matches("\\d*\\.?\\d*")) {
		        try {
		            double value = Double.parseDouble(text);
		            return value >= 0; // Ensure the value is non-negative
		        } catch (NumberFormatException e) {
		            return false; // Not a valid double
	        }
        } else {
            return false; // Contains invalid characters
        }
    }
}
