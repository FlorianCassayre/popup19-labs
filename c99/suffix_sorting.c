/*
 * Course: Problem Solving and Programming under Pressure (DD2458)
 * Type: Laboratory exercises
 * Authors:
 * - Eduardo Rodes Pastor (9406031931)
 * - Florian Cassayre (980703T092)
 */
#include <stdio.h>
#include <stdlib.h>

#define COMPARE(x, y) ((x) > (y) ? 1 : (x) < (y) ? -1 : 0)

typedef struct tuple_t {
    int index, rank0, rank1;
} tuple_t;

int tuple_comparator(const void *p, const void *q) {
    tuple_t a = *((tuple_t *) p), b = *((tuple_t *) q);

    if(a.rank0 == b.rank0)
        return COMPARE(a.rank1, b.rank1);
    else
        return COMPARE(a.rank0, b.rank0);
}

/**
 * Constructs the suffix array of a string.
 * @param string the input string
 * @param len the length of the string
 * @return the suffix array of that string
 */
int* suffix_array(char* string, size_t len) {
    const int n = (int) len;

    tuple_t* suffixes = malloc(n * sizeof(tuple_t));
    for(int i = 0; i < n; i++) {
        suffixes[i].index = i;
        suffixes[i].rank0 = (int) string[i];
        suffixes[i].rank1 = i + 1 < n ? (int) string[i + 1] : -1;
    }

    // Sort the suffixes by the first two characters (base case)
    qsort((void*) suffixes, len, sizeof(tuple_t), tuple_comparator);

    int* indices = malloc(n * sizeof(int));

    for(int k = 4; k < 2 * n; k <<= 1) {
        // At this point the suffixes are (k - 2) sorted

        int rank = 0;
        int prevRank = suffixes[0].rank0;
        suffixes[0].rank0 = 0;
        indices[suffixes[0].index] = 0;

        for(int i = 1; i < n; i++) {
            if(suffixes[i].rank0 == prevRank && suffixes[i].rank1 == suffixes[i - 1].rank1) {
                prevRank = suffixes[i].rank0;
                suffixes[i].rank0 = rank;
            } else {
                prevRank = suffixes[i].rank0;
                rank++;
                suffixes[i].rank0 = rank;
            }
            indices[suffixes[i].index] = i;
        }

        for(int i = 0; i < n; i++) {
            const int nextIndex = suffixes[i].index + (k >> 1);
            suffixes[i].rank1 = nextIndex < n ? suffixes[indices[nextIndex]].rank0 : -1;
        }

        // Sort by the next two characters
        qsort((void*) suffixes, len, sizeof(tuple_t), tuple_comparator);
    }

    int* array = malloc(n * sizeof(int));
    for(int i = 0; i < n; i++)
        array[i] = suffixes[i].index;

    free(suffixes);
    free(indices);

    return array;
}

// === IO ===

size_t read_line(char* buffer, size_t len) {
    const ssize_t read = getline(&buffer, &len, stdin) - 1;
    buffer[read] = '\0';
    return (size_t) read;
}

int next_int(char** string) {
    int n;
    sscanf(*string, "%d", &n);
    while((*string)[0] > ' ')
        (*string)++;
    (*string)++;
    return n;
}

int main(void) {
    const size_t size_string = 100000 + 2, size_queries = 588898; // Magic values! (these are just the size of the buffers)
    char *buffer_string = malloc(size_string * sizeof(char)), *buffer_queries = malloc(size_queries * sizeof(char));

    int peek;
    while((peek = getchar()) != -1) {
        ungetc(peek, stdin);

        const size_t string_length = read_line(buffer_string, size_string);
        read_line(buffer_queries, size_queries);

        int* const array = suffix_array(buffer_string, string_length);

        char* queries = buffer_queries;
        const int n = next_int(&queries);

        for(int i = 0; i < n; i++) {
            const int q = next_int(&queries), v = array[q];

            if(i < n - 1)
                printf("%d ", v);
            else
                printf("%d\n", v);
        }

        free(array);
    }

    free(buffer_string);
    free(buffer_queries);
}
