#include <sys/types.h>

struct send_file {
    char file_name[256];
    off_t file_size;
    mode_t mode;
    uid_t uid;
    gid_t gid;
};

struct ready_transaction {
    off_t offset;
};
