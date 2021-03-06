package org.gap.eclipse.jdt.hyperlinks;

import java.util.List;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.jdt.internal.ui.javaeditor.JavaElementHyperlinkDetector;
import org.eclipse.jdt.ui.actions.SelectionDispatchAction;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.hyperlink.IHyperlink;

@SuppressWarnings("restriction")
public class JavaReferenceHyperlinkDetector extends JavaElementHyperlinkDetector {
	@Override
	protected void addHyperlinks(List<IHyperlink> hyperlinksCollector, IRegion wordRegion,
			SelectionDispatchAction openAction, IJavaElement element, boolean qualify, JavaEditor editor) {
		
		if(shouldProcess(element)) {
			hyperlinksCollector.add(new JavaReferenceHyperlink(wordRegion, element));
		}
	}
	
	private boolean shouldProcess(IJavaElement element) {
		return (element instanceof IType && !((IType) element).isResolved()) || 
				(element instanceof IMethod && !((IMethod) element).isResolved()) ||
				(element instanceof IField && !((IField) element).isResolved());
	}
}
