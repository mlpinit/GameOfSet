#! /bin/bash

ant -logger org.apache.tools.ant.listener.AnsiColorLogger clean compile dist
