FROM public.ecr.aws/lambda/provided:al2

WORKDIR /var/task
COPY src/ /var/task/src/
COPY Cargo.toml Cargo.lock /var/task/

RUN yum install -y openssl-devel gcc 
RUN curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs -o rustup-init && \
    sh rustup-init -y && \
    source $HOME/.cargo/env
RUN export PATH=$HOME/.cargo/bin:$PATH

RUN $HOME/.cargo/bin/rustup update && \
    $HOME/.cargo/bin/rustup target add x86_64-unknown-linux-gnu
RUN $HOME/.cargo/bin/cargo build --release --target x86_64-unknown-linux-gnu

# 実行ファイルを起動するようにするため、ファイル名を "bootstrap" に変更する
RUN cp -a /var/task/target/x86_64-unknown-linux-gnu/release/rust-func ${LAMBDA_RUNTIME_DIR}/bootstrap

# カスタムランタイムはハンドラ名利用しないため、適当な文字列を指定する。
CMD [ "lambda-handler" ]
