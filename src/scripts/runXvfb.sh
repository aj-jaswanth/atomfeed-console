#!/bin/bash

kill `pgrep Xvfb` || true
Xvfb :99 &
export DISPLAY=:99