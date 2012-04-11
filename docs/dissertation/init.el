(require 'org-latex)
(org-babel-do-load-languages
 'org-babel-load-languages
 '((dot        . t)
   (emacs-lisp . t)
   (org        . t)
   (sh         . t)
   (gnuplot    . t)))
(setq org-confirm-babel-evaluate nil)
(add-to-list 'org-export-latex-classes
             '("dissertation"
               "
% !TEX TS-program = xelatex
% !TEX encoding = UTF-8 Unicode
\\documentclass{article}
\\usepackage{fontspec}
\\usepackage{xunicode}
\\usepackage{xltxtra}
\\usepackage{xunicode}
\\usepackage{xltxtra}
\\defaultfontfeatures{Mapping=tex-text}
\\setromanfont{Linux Libertine O}
\\setmonofont[Scale=0.8]{Monaco}
\\usepackage{geometry}
\\usepackage{setspace}
\\usepackage{wrapfig}
\\usepackage{graphicx}
\\usepackage{mathtools}
\\usepackage{paralist}
\\usepackage{longtable}
\\usepackage{float}
\\usepackage{wrapfig}
\\usepackage{soul}
\\usepackage{textcomp}
\\usepackage{marvosym}
\\usepackage{latexsym}
\\usepackage{amssymb}
\\usepackage{hyperref}
\\usepackage{setspace}
\\tolerance=1000
\\usepackage{color}
\\usepackage{listings}
[NO-DEFAULT-PACKAGES]
[NO-PACKAGES]"
               ("\\section{%s}" . "\\section*{%s}")
               ("\\subsection{%s}" . "\\subsection*{%s}")
               ("\\subsubsection{%s}" . "\\subsubsection*{%s}")
               ("\\paragraph{%s}" . "\\paragraph*{%s}")
               ("\\subparagraph{%s}" . "\\subparagraph*{%s}")))
(setq org-latex-to-pdf-process 
  '("xelatex -interaction nonstopmode %f"
     "xelatex -interaction nonstopmode %f")) ;; for multiple passes
