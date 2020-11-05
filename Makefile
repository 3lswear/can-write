JCC = javac
JCR = java
CFLAGS = \
	-d $(BUILDDIR)
RFLAGS = \
	-cp $(BUILDDIR)

SOURCEDIR = src
BUILDDIR = out
NAME = kurs

SOURCES = $(wildcard $(SOURCEDIR)/*.java)
CLASSES = $(patsubst $(SOURCEDIR)/%.java,$(BUILDDIR)/%.class,$(SOURCES))

all: $(NAME)

$(NAME): $(CLASSES)
	$(JCC) $(CFLAGS) $(SOURCES)

run: $(NAME)
	$(JCR) $(RFLAGS) Main

clean:
	$(RM) $(BUILDDIR)/*.class
