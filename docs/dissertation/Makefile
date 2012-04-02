CC=gcc
EMACS=/Applications/Emacs.app/Contents/MacOS/Emacs
BATCH_EMACS=$(EMACS) --batch -Q -l init.el dissertation.org
REQUIREMENTS=sh

all: dissertation.pdf

dissertation.tex: dissertation.org init.el
	$(BATCH_EMACS) -f org-export-as-latex

dissertation.pdf: dissertation.tex
	rm -f dissertation.aux 
	if pdflatex dissertation.tex </dev/null; then \
		true; \
	else \
		stat=$$?; touch dissertation.pdf; exit $$stat; \
	fi
	bibtex dissertation
	while grep "Rerun to get" dissertation.log; do \
		if pdflatex dissertation.tex </dev/null; then \
			true; \
		else \
			stat=$$?; touch dissertation.pdf; exit $$stat; \
		fi; \
	done

dissertation.ps: dissertation.pdf
	pdf2ps dissertation.pdf

check:
	for req in $(REQUIREMENTS); do \
		which $$req > /dev/null || echo "MISSING DEPENDENCY $$req"; \
	done

clean:
	rm -f *.aux *.log dissertation.ps *.dvi *.blg *.bbl *.toc *.tex *~ *.out dissertation.pdf cocktail*

real-clean: clean
	rm -f *.pdf
